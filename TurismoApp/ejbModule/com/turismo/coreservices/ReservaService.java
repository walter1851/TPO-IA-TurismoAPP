package com.turismo.coreservices;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.rpc.ServiceException;

//import com.turismo.backoffice.autorizacion.*;
import com.turismo.dao.OfertaBloqueDAO;
import com.turismo.dao.OfertaDAO;
import com.turismo.dao.ReservaDAO;
import com.turismo.dto.ReservaDTO;
import com.turismo.entities.OfertaBloque;
import com.turismo.entities.OfertaTipo;
import com.turismo.entities.Reserva;
import com.turismo.exceptions.ConversionFechaException;
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
	private OfertaDAO ofertaDAO;
	@EJB
	BusquedaService busquedaService;
	// @EJB
	// private SOAPService soapService;

	public ReservaDTO reservarPaquete(int ofertaid, String fDesde, String fHasta, int cantPersonas, String nombre,
			String apellido, String dni, int medioPagoID, String emailUsuario)
			throws ReservaException, RemoteException, ServiceException, ConversionFechaException {
		ReservaDTO nuevaReservaDTO = null;
		LocalDate fDesdeConverted = busquedaService.convertStringToLocalDate(fDesde);
		LocalDate fHastaConverted = busquedaService.convertStringToLocalDate(fHasta);
		boolean formatoFechaOK = busquedaService.validarRangoFechaPaquete(fDesdeConverted, fHastaConverted);
		List<OfertaBloque> bloques = ofertaBloqueDAO.buscarBloquesDePaquetes(ofertaid, fDesdeConverted, fHastaConverted,
				cantPersonas);
		boolean hayDisponibilidad = this.validarDisponibilidadPaquete(bloques);
		// consulto al backoffice si puedo reservar
		// backOfficeAutorizador.getServicioPrestadorAutorizadoPort().getPrestadorAutorizado(1);
		// boolean puedoReservar = soapService.getSOAPPort().estaAutorizado(1);
		boolean puedoReservar = true;
		if (!formatoFechaOK)
			throw new ReservaException(
					"La fechas ingresadas no se encuentran dentro del rango esperado. Verifique que la fecha de inicio "
							+ fDesdeConverted.toString() + " es menor que la fecha de salida " + fHastaConverted
							+ " , y que dicho rango se encuentre dentro de la fecha actual "
							+ LocalDate.now().toString());
		if (!hayDisponibilidad)
			throw new ReservaException("No hay disponibilidad desde la fecha " + fDesdeConverted.toString() + " hasta "
					+ fDesdeConverted.toString() + " para la cantidad de " + cantPersonas + " persona/s");
		if (!puedoReservar)
			throw new ReservaException("No hay autorizacion del backoffice para reservar");
		Reserva nuevaReservaPaquete = null;
		float montoTotal = 0;
		if (formatoFechaOK && hayDisponibilidad && puedoReservar)
			montoTotal = montoTotal + (ofertaDAO.buscarPorIdOferta(ofertaid).getPrecio() * cantPersonas);
		if (actualizarCupoBloquesPaquete(bloques))
			nuevaReservaPaquete = reservaDAO.crearReserva(ofertaid, 1, fDesdeConverted, fHastaConverted, medioPagoID,
					nombre, apellido, emailUsuario, dni, montoTotal);
		if (nuevaReservaPaquete == null)
			throw new ReservaException("No se puedo grabar la reserva en la base de datos.");
		else
			nuevaReservaDTO = mapperService.obtenerReservaDTO(nuevaReservaPaquete);
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
			throws ReservaException, ConversionFechaException {
		ReservaDTO nuevaReservaDTO = null;
		// valido el formato de las fechas
		LocalDate fDesdeConverted = busquedaService.convertStringToLocalDate(fDesde);
		LocalDate fHastaConverted = busquedaService.convertStringToLocalDate(fHasta);
		int cantDiasHotel = fDesdeConverted.compareTo(fHastaConverted);
		boolean formatoFechaOK = busquedaService.validarRangoFechaHotelera(fDesdeConverted, fHastaConverted);
		List<OfertaBloque> bloques = ofertaBloqueDAO.buscarBloquesDeHoteleria(ofertaid, fDesdeConverted,
				fHastaConverted, tipoHabitacion);
		boolean hayDisponibilidad = this.validarDisponibilidadHotelera(bloques, cantHabitaciones);
		// consulto al backoffice si puedo reservar
		boolean puedoReservar = true/* completar despues c backoffice */;
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
		boolean cupoActualizado = false;
		if (formatoFechaOK && hayDisponibilidad && puedoReservar)
			cupoActualizado = actualizarCupoBloquesHotelero(bloques, cantHabitaciones);
		Reserva nuevaReservaHotelera = null;
		if (cupoActualizado) {
			float montoTotal = ofertaDAO.buscarPorIdOferta(ofertaid).getPrecio() * cantHabitaciones * cantDiasHotel;
			nuevaReservaHotelera = reservaDAO.crearReserva(ofertaid, 1, fDesdeConverted, fHastaConverted, medioPagoID,
					nombre, apellido, emailUsuario, dni, montoTotal);
			nuevaReservaDTO = mapperService.obtenerReservaDTO(nuevaReservaHotelera);
		}
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
