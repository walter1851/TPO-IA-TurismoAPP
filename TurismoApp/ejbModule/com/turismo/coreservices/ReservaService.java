package com.turismo.coreservices;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;
import javax.ejb.TransactionManagementType;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.xml.rpc.ServiceException;

import com.backoffice.servicios.SOAPService;
import com.turismo.dao.OfertaBloqueDAO;
import com.turismo.dao.OfertaDAO;
import com.turismo.dao.ReservaDAO;
import com.turismo.dto.ReservaDTO;
import com.turismo.entities.OfertaBloque;
import com.turismo.entities.OfertaTipo;
import com.turismo.entities.Reserva;
import com.turismo.entities.TipoHabitacion;
import com.turismo.exceptions.ConversionFechaException;
import com.turismo.exceptions.OfertaHoteleraException;
import com.turismo.exceptions.OfertaPaqueteException;
import com.turismo.exceptions.ReservaException;

@Stateless
@LocalBean
@TransactionManagement(value = TransactionManagementType.BEAN)
public class ReservaService {
	@EJB
	private ReservaDAO reservaDAO;
	@EJB
	private OfertaBloqueDAO ofertaBloqueDAO;
	@EJB
	private MapperService mapperService;
	@EJB
	BusquedaService busquedaService;
	@EJB
	private OfertaDAO ofertaDAO;
	@Resource
	private UserTransaction userTransaction;

	public boolean prestadorEstaAutorizado(String codigo_prestador) {
		SOAPService service = new SOAPService();
		return service.getSOAPPort().estaAutorizado(codigo_prestador);
		//return true;
	}

	public ReservaDTO reservarPaquete(int ofertaid, String fDesde, String fHasta, int cantPersonas, String nombre,
			String apellido, String dni, int medioPagoID, String emailUsuario) throws ReservaException, RemoteException,
			ServiceException, ConversionFechaException, OfertaPaqueteException {
		Reserva nuevaReservaPaquete = null;
		ReservaDTO nuevaReservaDTO = null;
		boolean hayDisponibilidad = true;
		LocalDate fDesdeConverted = busquedaService.convertStringFReservaToLocalDate(fDesde);
		LocalDate fHastaConverted = busquedaService.convertStringFReservaToLocalDate(fHasta);
		boolean formatoFechaOK = busquedaService.validarRangoFechaPaquete(fDesdeConverted, fHastaConverted);
		boolean ofertaExistente = busquedaService.existeOfertaPaquete(ofertaid);
		boolean cupoActualizado = false;
		boolean estaAutorizado = false;
		if (ofertaExistente) {
			String codigo_agencia = ofertaDAO.buscarPorIdOferta(ofertaid).getAgencia().getCodigo_agencia();
			estaAutorizado = prestadorEstaAutorizado(codigo_agencia);
			List<OfertaBloque> bloques = ofertaBloqueDAO.buscarBloquesDePaquetes(ofertaid, fDesdeConverted,
					fHastaConverted, cantPersonas);
			hayDisponibilidad = busquedaService.validarDisponibilidadPaquete(bloques);
			try {
				userTransaction.begin();
				if (estaAutorizado && formatoFechaOK && hayDisponibilidad) {
					cupoActualizado = actualizarCupoBloquesPaquete(bloques);
					if (cupoActualizado) {
						float montoTotal = busquedaService.calcularPrecioTotalPaquete(ofertaid, cantPersonas);
						nuevaReservaPaquete = reservaDAO.crearReserva(ofertaid, fDesdeConverted, fHastaConverted,
								medioPagoID, nombre, apellido, emailUsuario, dni, montoTotal);
						nuevaReservaDTO = mapperService.obtenerReservaDTO(nuevaReservaPaquete);
					}
				}
				userTransaction.commit();
				if (!estaAutorizado)
					throw new ReservaException("El codigo de prestador (codigo agencia) "
							+ " no se encuentra autorizado por el backoffice. ");
			} catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException
					| RollbackException e) {
				e.printStackTrace();
			}
			if (bloques.isEmpty())
				throw new ReservaException(
						"No se encontraron bloques de paquete que cumplan con fecha desde "
								+ fDesdeConverted.toString() + " hasta " + fHastaConverted.toString() + " cantidad "
								+ cantPersonas + " persona/s.");
		}
		if (!ofertaExistente)
			throw new ReservaException(
					"El id de oferta (id interno): " + ofertaid + " no existe en la base de datos. ");
		try {
			if (!cupoActualizado || nuevaReservaPaquete == null) {
				userTransaction.rollback();
				throw new ReservaException(
						"No se pudo completar la transacción. La reserva no fue grabada en la base de datos.");
			}
		} catch (IllegalStateException | SecurityException | SystemException e) {
			e.printStackTrace();
		}

		return nuevaReservaDTO;
	}

	private boolean actualizarCupoBloquesPaquete(List<OfertaBloque> bloques) throws ReservaException {
		boolean cupoActualizado = true;
		for (OfertaBloque ofertaBloque : bloques) {
			// valido que sea consistente el id que me pasan con la oferta paquete.
			if (!ofertaBloque.getOferta().getOfertaTipo().equals(OfertaTipo.OFERTA_PAQUETE)) {
				cupoActualizado = false;
				throw new ReservaException(
						"NO SE ACTUALIZARON LOS BLOQUES. ERROR GRAVE DE CONSISTENCIA. NO COINCIDE EL ID DE LA OFERTA PAQUETE CON EL TIPO DE OFERTA ASOCIADO EN LA BASE DE DATOS.");
			} else {
				ofertaBloque.setCupo(ofertaBloque.getCupo() - 1);
				if (!ofertaBloqueDAO.actualizarBloque(ofertaBloque)) {
					cupoActualizado = false;
					throw new ReservaException("NO SE PUDIERON ACTUALIZAR LOS CUPOS EN LOS BLOQUES DE LA BD.");
				}
			}
		}
		return cupoActualizado;
	}

	public ReservaDTO reservarHotel(int ofertaid, String fDesde, String fHasta, String tipoHabString,
			int cantTotalPersonas, String nombre, String apellido, String dni, int medioPagoID, String emailUsuario)
			throws ReservaException, ConversionFechaException, OfertaHoteleraException, OfertaPaqueteException {
		ReservaDTO nuevaReservaDTO = null;
		Reserva nuevaReservaHotelera = null;
		boolean estaAutorizado = true;
		boolean hayDisponibilidad = true;
		boolean cupoActualizado = false;
		// valido el formato de las fechas
		LocalDate fDesdeConverted = busquedaService.convertStringFReservaToLocalDate(fDesde);
		LocalDate fHastaConverted = busquedaService.convertStringFReservaToLocalDate(fHasta);
		boolean formatoFechaOK = busquedaService.validarRangoFechaHotelera(fDesdeConverted, fHastaConverted);
		boolean ofertaExistente = busquedaService.existeOfertaHotelera(ofertaid);
		TipoHabitacion tipoHabitacion = TipoHabitacion.valueOf(tipoHabString);
		int cantHabitaciones = busquedaService.calcularTotalHabitaciones(cantTotalPersonas, tipoHabitacion);
		if (ofertaExistente) {
			String codigo_establecimiento = ofertaDAO.buscarPorIdOferta(ofertaid).getEstablecimiento()
					.getCodigo_establecimiento();
			estaAutorizado = prestadorEstaAutorizado(codigo_establecimiento);
			//Aca me traigo todos los bloques menos el ultimo. Ya que se reserva por noche.
			List<OfertaBloque> bloques = ofertaBloqueDAO.buscarBloquesDeHoteleria(ofertaid, fDesdeConverted,
					fHastaConverted.minusDays(1), tipoHabitacion);
			hayDisponibilidad = busquedaService.validarDisponibilidadHotelera(bloques, cantHabitaciones);
			try {
				userTransaction.begin();
				if (formatoFechaOK && hayDisponibilidad && estaAutorizado)
					cupoActualizado = actualizarCupoBloquesHotelero(bloques, cantHabitaciones);
				if (cupoActualizado) {
					float montoTotal = busquedaService.calcularPrecioTotalHotel(ofertaid,
							tipoHabitacion.getTipoHabitacion(), cantTotalPersonas, fDesde, fHasta);
					nuevaReservaHotelera = reservaDAO.crearReserva(ofertaid, fDesdeConverted, fHastaConverted,
							medioPagoID, nombre, apellido, emailUsuario, dni, montoTotal);
					nuevaReservaDTO = mapperService.obtenerReservaDTO(nuevaReservaHotelera);
				}
				userTransaction.commit();
				if (!estaAutorizado)
					throw new ReservaException("El codigo de prestador (codigo del establecimiento) no se encuentra autorizado por el backoffice. ");
			} catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException
					| RollbackException e) {
				e.printStackTrace();
			}
			if (bloques.isEmpty())
				throw new ReservaException(
						"No se encontraron bloques de paquete que cumplan con fecha desde " + fDesdeConverted.toString() + " hasta "
								+ fHastaConverted.toString() + " cantidad de habitaciones " + cantHabitaciones);
		}
		if (!ofertaExistente)
			throw new ReservaException(
					"El id de oferta (id interno): " + ofertaid + " no existe en la base de datos. ");
		try {
			if (!cupoActualizado || nuevaReservaHotelera == null) {
				userTransaction.rollback();
				throw new ReservaException(
						"No se pudo completar la transacción. La reserva no fue grabada en la base de datos.");
			}
		} catch (IllegalStateException | SecurityException | SystemException e) {
			e.printStackTrace();
		}

		return nuevaReservaDTO;
	}

	private boolean actualizarCupoBloquesHotelero(List<OfertaBloque> bloques, int cantHabitaciones)
			throws ReservaException {
		boolean cupoActualizado = true;
		boolean hayConsistencia = true;
		for (OfertaBloque ofertaBloque : bloques) {
			// valido que sea consistente el id que me pasan con la ofertaHotelera, y no de
			// paquete.
			if (!ofertaBloque.getOferta().getOfertaTipo().equals(OfertaTipo.OFERTA_HOTELERA))
				hayConsistencia = false;
			if (hayConsistencia) {
				// Descontamos el cupo segun la cantidad de habitaciones
				int cantDescontarCupo = 1 * cantHabitaciones;
				ofertaBloque.setCupo(ofertaBloque.getCupo() - cantDescontarCupo);
				if (!ofertaBloqueDAO.actualizarBloque(ofertaBloque))
					cupoActualizado = false;
			}
		}
		if (!cupoActualizado)
			throw new ReservaException("No se pudo actualizar el cupo en la base de datos.");
		if (!hayConsistencia)
			throw new ReservaException(
					"NO SE ACTUALIZARON LOS BLOQUES. ERROR GRAVE DE CONSISTENCIA. NO COINCIDE EL ID DE LA OFERTA HOTELERA CON EL TIPO DE OFERTA ASOCIADO EN LA BASE DE DATOS.");
		return cupoActualizado && hayConsistencia;
	}
}
