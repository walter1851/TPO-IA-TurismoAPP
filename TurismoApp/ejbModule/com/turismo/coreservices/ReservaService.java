package com.turismo.coreservices;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.rpc.ServiceException;

import com.turismo.backoffice.autorizacion.SOAPService;
import com.turismo.dao.OfertaBloqueDAO;
import com.turismo.dao.ReservaDAO;
import com.turismo.dto.ReservaDTO;
import com.turismo.entities.OfertaBloque;
import com.turismo.entities.OfertaTipo;
import com.turismo.entities.Reserva;
import com.turismo.exceptions.ConversionFechaException;
import com.turismo.exceptions.OfertaHoteleraException;
import com.turismo.exceptions.OfertaPaqueteException;
import com.turismo.exceptions.ReservaException;

@Stateless
@LocalBean
public class ReservaService {
	@EJB
	private ReservaDAO reservaDAO;
	@EJB
	private OfertaBloqueDAO ofertaBloqueDAO;
	@EJB
	private MapperService mapperService;
	@EJB
	BusquedaService busquedaService;
	//@EJB
	//private SOAPService soapService;

	public ReservaDTO reservarPaquete(int ofertaid, String fDesde, String fHasta, int cantPersonas, String nombre,
			String apellido, String dni, int medioPagoID, String emailUsuario) throws ReservaException, RemoteException,
			ServiceException, ConversionFechaException, OfertaPaqueteException {
		ReservaDTO nuevaReservaDTO = null;
		boolean puedoReservar = true;
		boolean hayDisponibilidad = true;
		LocalDate fDesdeConverted = busquedaService.convertStringToLocalDate(fDesde);
		LocalDate fHastaConverted = busquedaService.convertStringToLocalDate(fHasta);
		boolean formatoFechaOK = busquedaService.validarRangoFechaPaquete(fDesdeConverted, fHastaConverted);
		boolean ofertaExistente = busquedaService.existeOfertaPaquete(ofertaid);
		if (ofertaExistente) {
			// consulto al backoffice si puedo reservar, le paso el codigo externo de la
			// agencia (al ser paquete)
			// backOfficeAutorizador.getServicioPrestadorAutorizadoPort().getPrestadorAutorizado(1);
			//puedoReservar = soapService.getSOAPPort().estaAutorizado(1);
			List<OfertaBloque> bloques = ofertaBloqueDAO.buscarBloquesDePaquetes(ofertaid, fDesdeConverted,
					fHastaConverted, cantPersonas);
			hayDisponibilidad = this.validarDisponibilidadPaquete(bloques);
			Reserva nuevaReservaPaquete = null;
			if (formatoFechaOK && hayDisponibilidad && puedoReservar) {
				float montoTotal = busquedaService.calcularPrecioTotalPaquete(ofertaid, cantPersonas);
				if (actualizarCupoBloquesPaquete(bloques))
					nuevaReservaPaquete = reservaDAO.crearReserva(ofertaid, 1, fDesdeConverted, fHastaConverted,
							medioPagoID, nombre, apellido, emailUsuario, dni, montoTotal);
			}
			if (nuevaReservaPaquete == null)
				throw new ReservaException("No se puedo grabar la reserva en la base de datos.");
			else
				nuevaReservaDTO = mapperService.obtenerReservaDTO(nuevaReservaPaquete);
		}		
		if (!formatoFechaOK)
			throw new ReservaException(
					"La fechas ingresadas no se encuentran dentro del rango esperado. Verifique que la fecha de inicio "
							+ fDesdeConverted.toString() + " es menor que la fecha de salida " + fHastaConverted
							+ " , y que dicho rango se encuentre dentro de la fecha actual "
							+ LocalDate.now().toString());
		if (!hayDisponibilidad)
			throw new ReservaException("No hay disponibilidad desde la fecha " + fDesdeConverted.toString() + " hasta "
					+ fHastaConverted.toString() + " para la cantidad " + cantPersonas
					+ " persona/s. Tenga en cuenta que el rango de fechas involucrado en el paquete debe estar contenido en las fechas ingresadas.");
		if (!ofertaExistente)
			throw new ReservaException(
					"El id de oferta (id interno): " + ofertaid + " no existe en la base de datos. ");
		if (!puedoReservar)
			throw new ReservaException("No hay autorizacion del backoffice para reservar");

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
				// Descontamos uno al cupo
				ofertaBloque.setCupo(ofertaBloque.getCupo() - 1);
				if (!ofertaBloqueDAO.actualizarBloque(ofertaBloque)) {
					cupoActualizado = false;
					throw new ReservaException("NO SE PUDIERON ACTUALIZAR LOS CUPOS EN LOS BLOQUES DE LA BD.");
				}
			}
		}
		return cupoActualizado;
	}

	public ReservaDTO reservarHotel(int ofertaid, String fDesde, String fHasta, String tipoHabitacion,
			int cantHabitaciones, String nombre, String apellido, String dni, int medioPagoID, String emailUsuario)
			throws ReservaException, ConversionFechaException, OfertaHoteleraException {
		ReservaDTO nuevaReservaDTO = null;
		boolean puedoReservar = true;
		boolean hayDisponibilidad = true;
		Reserva nuevaReservaHotelera = null;
		// valido el formato de las fechas
		LocalDate fDesdeConverted = busquedaService.convertStringToLocalDate(fDesde);
		LocalDate fHastaConverted = busquedaService.convertStringToLocalDate(fHasta);
		boolean formatoFechaOK = busquedaService.validarRangoFechaHotelera(fDesdeConverted, fHastaConverted);
		boolean ofertaExistente = busquedaService.existeOfertaHotelera(ofertaid);
		if (ofertaExistente) {
			// consulto al backoffice si puedo reservar, le paso el codigo externo del
			// establecimiento (al ser hotel)
			puedoReservar = true/* completar despues c backoffice */;
			List<OfertaBloque> bloques = ofertaBloqueDAO.buscarBloquesDeHoteleria(ofertaid, fDesdeConverted,
					fHastaConverted, tipoHabitacion);
			hayDisponibilidad = this.validarDisponibilidadHotelera(bloques, cantHabitaciones);
			boolean cupoActualizado = false;
			if (formatoFechaOK && hayDisponibilidad && puedoReservar)
				cupoActualizado = actualizarCupoBloquesHotelero(bloques, cantHabitaciones);
			if (cupoActualizado) {
				float montoTotal = busquedaService.calcularPrecioTotalHotel(ofertaid, cantHabitaciones, fDesde,fHasta);
				nuevaReservaHotelera = reservaDAO.crearReserva(ofertaid, 1, fDesdeConverted, fHastaConverted,
						medioPagoID, nombre, apellido, emailUsuario, dni, montoTotal);
				nuevaReservaDTO = mapperService.obtenerReservaDTO(nuevaReservaHotelera);
			}
		}
		if (!formatoFechaOK)
			throw new ReservaException(
					"La fechas ingresadas no se encuentran dentro del rango esperado. Verifique que la fecha de inicio "
							+ fDesdeConverted.toString() + " es menor que la fecha de salida " + fHastaConverted
							+ " , y que dicho rango se encuentre dentro de la fecha actual "
							+ LocalDate.now().toString());
		if (!hayDisponibilidad)
			throw new ReservaException(
					"No hay disponibilidad desde la fecha: " + fDesdeConverted.toString() + " hasta: "
							+ fHastaConverted.toString() + " para la cantidad de habitaciones: " + cantHabitaciones);
		if (!puedoReservar)
			throw new ReservaException("No hay autorizacion del backoffice para reservar");
		if (!ofertaExistente)
			throw new ReservaException(
					"El id de oferta (id interno): " + ofertaid + " no existe en la base de datos. ");
		if (nuevaReservaHotelera == null)
			throw new ReservaException("No se puedo grabar la reserva en la base de datos.");
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

	private boolean validarDisponibilidadHotelera(List<OfertaBloque> bloques, int cantHabitaciones) {
		if (bloques.isEmpty())
			return false;
		else {
			boolean disponibilidad = true;
			for (OfertaBloque ofertaBloque : bloques) {
				if ((ofertaBloque.getCupo() - (cantHabitaciones * 1)) < 0) {
					disponibilidad = false;
				}
			}
			return disponibilidad;
		}
	}

	private boolean validarDisponibilidadPaquete(List<OfertaBloque> bloques) {
		if (bloques.isEmpty())
			return false;
		else {
			boolean disponibilidad = true;
			for (OfertaBloque ofertaBloque : bloques) {
				if ((ofertaBloque.getCupo() - 1) < 0) {
					disponibilidad = false;
				}
			}
			return disponibilidad;
		}
	}
}
