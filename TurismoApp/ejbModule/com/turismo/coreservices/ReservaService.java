package com.turismo.coreservices;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.rpc.ServiceException;

import com.turismo.backoffice.autorizacion.*;
import com.turismo.dao.OfertaBloqueDAO;
import com.turismo.dao.ReservaDAO;
import com.turismo.dto.ReservaDTO;
import com.turismo.entities.OfertaBloque;
import com.turismo.entities.OfertaTipo;
import com.turismo.entities.Reserva;
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
	//@EJB 
	//private SOAPService soapService;

	public ReservaDTO reservarPaquete(int ofertaid, String fDesde, String fHasta, int cantPersonas, String nombre,
			String apellido, String dni, int medioPagoID, String emailUsuario) throws ReservaException, RemoteException, ServiceException {
		ReservaDTO nuevaReservaDTO=null;
		float montoTotal = 0;
		// valido el formato de las fechas
		LocalDate fDesdeConverted;
		LocalDate fHastaConverted;
		try {
			fDesdeConverted = convertStringToLocalDate(fDesde);
			fHastaConverted = convertStringToLocalDate(fHasta);
		} catch (ParseException e) {
			throw new ReservaException(
					"El formato de la fechas ingresadas no es valido. Ejemplo fecha valida: 2018-04-05T12:30-02:00");
		}
		boolean formatoFechaOK = validarFechaReserva(fDesdeConverted, fHastaConverted);
		List<OfertaBloque> bloques = ofertaBloqueDAO.buscarBloquesDePaquetes(ofertaid, fDesdeConverted, fHastaConverted,
				cantPersonas);
		boolean hayDisponibilidad;
		if (bloques.isEmpty())
			hayDisponibilidad = false;
		else
			hayDisponibilidad = this.validarDispPaquete(bloques);
		// consulto al backoffice si puedo reservar	
		/* completar despues */;
		// backOfficeAutorizador.getServicioPrestadorAutorizadoPort().getPrestadorAutorizado(1);
		//boolean puedoReservar = soapService.getSOAPPort().estaAutorizado(1);
		boolean puedoReservar = true;
		if (!formatoFechaOK)
			throw new ReservaException("La fecha ingresada no tiene el formato adecuado.");
		if (!hayDisponibilidad)
			throw new ReservaException("No hay disponibilidad desde la fecha " + fDesdeConverted.toString() + " hasta " + fDesdeConverted.toString()
					+ " para la cantidad de " + cantPersonas + " persona/s");
		if (!puedoReservar)
			throw new ReservaException("No hay autorizacion del backoffice para reservar");

		Reserva nuevaReservaPaquete = null;
		if (formatoFechaOK && hayDisponibilidad && puedoReservar) {
			boolean hayConsistencia = true;
			boolean cupoActualizado = true;
			for (OfertaBloque ofertaBloque : bloques) {
				// valido que sea consistente el id que me pasan con la oferta paquete.
				if (!ofertaBloque.getOferta().getOfertaTipo().equals(OfertaTipo.OFERTA_PAQUETE))
					hayConsistencia = false;
				// Descontamos uno al cupo
				ofertaBloque.setCupo(ofertaBloque.getCupo() - 1);
				if (ofertaBloqueDAO.actualizarBloque(ofertaBloque))
					montoTotal = montoTotal + (ofertaBloque.getOferta().getPrecio() * cantPersonas);
				else
					cupoActualizado = false;
			}
			if (cupoActualizado && hayConsistencia) {
				nuevaReservaPaquete = reservaDAO.crearReserva(ofertaid,1,fDesdeConverted,fHastaConverted, medioPagoID, nombre, apellido, emailUsuario,
						dni, montoTotal);
				nuevaReservaDTO=mapperService.obtenerReservaDTO(nuevaReservaPaquete);
			}
			if (!hayConsistencia)
				throw new ReservaException("El id de oferta enviado, no corresponde a una oferta paquete valida.");
			if (!cupoActualizado)
				throw new ReservaException("No se pudo actualizar el cupo en la base de datos.");
			if (nuevaReservaPaquete == null)
				throw new ReservaException("No se puedo grabar la reserva en la base de datos.");
		}
		return nuevaReservaDTO;
	}

	public ReservaDTO reservarHotel(int ofertaid, String fDesde, String fHasta, String tipoHabitacion,
			int cantHabitaciones, String nombre, String apellido, String dni, int medioPagoID, String emailUsuario)
			throws ReservaException {
		ReservaDTO nuevaReservaDTO=null;
		float montoTotal = 0;
		// valido el formato de las fechas
		LocalDate fDesdeConverted;
		LocalDate fHastaConverted;
		try {
			fDesdeConverted = convertStringToLocalDate(fDesde);
			fHastaConverted = convertStringToLocalDate(fHasta);
		} catch (ParseException e) {
			throw new ReservaException(
					"El formato de la fechas involucradas en la reserva no es el correcto. Ejemplo fecha valida: 2018-04-05T12:30-02:00");
		}
		// valido el formato de las fechas
		boolean formatoFechaOK = validarFechaReserva(fDesdeConverted, fHastaConverted);
		List<OfertaBloque> bloques = ofertaBloqueDAO.buscarBloquesDeHoteleria(ofertaid, fDesdeConverted,
				fHastaConverted, tipoHabitacion);

		boolean hayDisponibilidad;
		if (bloques.isEmpty())
			hayDisponibilidad = false;
		else
			hayDisponibilidad = this.validarDispHabitaciones(bloques, cantHabitaciones);
		// consulto al backoffice si puedo reservar
		boolean puedoReservar = true/* completar despues c backoffice */;

		if (!formatoFechaOK)
			throw new ReservaException("La fecha ingresada no tiene el formato adecuado.");
		if (!hayDisponibilidad)
			throw new ReservaException("No hay disponibilidad desde la fecha: " + fDesdeConverted.toString() + " hasta: " + fHastaConverted.toString()
					+ " para la cantidad de habitaciones: " + cantHabitaciones);
		if (!puedoReservar)
			throw new ReservaException("No hay autorizacion del backoffice para reservar");

		if (formatoFechaOK && hayDisponibilidad && puedoReservar) {
			boolean hayConsistencia = true;
			boolean cupoActualizado = true;
			for (OfertaBloque ofertaBloque : bloques) {
				// valido que sea consistente el id que me pasan con la ofertaHotelera, y no de
				// paquete.
				if (!ofertaBloque.getOferta().getOfertaTipo().equals(OfertaTipo.OFERTA_HOTELERA))
					hayConsistencia = false;
				// Descontamos el cupo segun la cantidad de habitaciones
				int cantDescontarCupo = 1 * cantHabitaciones;
				ofertaBloque.setCupo(ofertaBloque.getCupo() - cantDescontarCupo);
				if (ofertaBloqueDAO.actualizarBloque(ofertaBloque))
					montoTotal = montoTotal + (ofertaBloque.getOferta().getPrecio() * cantHabitaciones);
				else
					cupoActualizado = false;
			}
			Reserva nuevaReservaHotelera = null;
			if (cupoActualizado && hayConsistencia) {
				nuevaReservaHotelera = reservaDAO.crearReserva(ofertaid, 1,fDesdeConverted,fHastaConverted, medioPagoID, nombre, apellido, emailUsuario,
						dni, montoTotal);
				nuevaReservaDTO=mapperService.obtenerReservaDTO(nuevaReservaHotelera);
			}
			if (!cupoActualizado)
				throw new ReservaException("No se pudo actualizar el cupo en la base de datos.");
			if (!hayConsistencia)
				throw new ReservaException("El id de oferta enviado, no corresponde a una oferta hotelera valida.");
			if (nuevaReservaHotelera == null)
				throw new ReservaException("No se puedo grabar la reserva en la base de datos.");
		}
		return nuevaReservaDTO;
	}

	private boolean validarDispHabitaciones(List<OfertaBloque> bloques, int cantHabitaciones) {
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

	private boolean validarDispPaquete(List<OfertaBloque> bloques) {
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

	private LocalDate convertStringToLocalDate(String stringFecha) throws ParseException {
		// Estamos validando que la fecha tenga el formato correcto
		// ejemplo 2018-06-20T12:30-02:00
		DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
		// ISO_OFFSET_DATE_TIME Date Time with Offset 2011-12-03T10:15:30+01:00'
		LocalDate dateTime = LocalDate.parse(stringFecha, formatter);
		return dateTime;
	}

	private boolean validarFechaReserva(LocalDate onlyDateDesde, LocalDate onlyDateHasta) {
		/*
		 * Estamos validando que fDesde no sea mayor que fHasta y que la fecha actual se
		 * encuentre dentro de dichos rangos
		 */
		LocalDate onlyDateFechaActual = LocalDate.now();
		if (onlyDateDesde.compareTo(onlyDateFechaActual) >= 0 && onlyDateHasta.compareTo(onlyDateFechaActual) >= 0
				&& onlyDateHasta.compareTo(onlyDateDesde) >= 0)
			return true;
		else
			return false;
	}
}
