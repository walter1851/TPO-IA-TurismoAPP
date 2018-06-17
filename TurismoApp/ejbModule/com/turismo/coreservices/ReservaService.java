package com.turismo.coreservices;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.turismo.dao.OfertaBloqueDAO;
import com.turismo.dao.ReservaDAO;
import com.turismo.dto.ReservaDTO;
import com.turismo.entities.OfertaBloque;
import com.turismo.entities.OfertaTipo;
import com.turismo.entities.Reserva;
import com.turismo.exceptions.OfertaPaqueteException;
import com.turismo.exceptions.ReservaException;
import com.turismo.integraciones.backoffice.autorizacion.BackOfficeAutorizacion;

@Stateless
@LocalBean
public class ReservaService {
	@EJB
	private ReservaDAO reservaDAO;
	@EJB
	private OfertaBloqueDAO ofertaBloqueDAO;
	@EJB
	private MapperService mapperService;
	/*
	 * @EJB private BackOfficeAutorizacion backOfficeAutorizador;
	 */

	public ReservaDTO reservarPaquete(int ofertaid, String fDesde, String fHasta, int cantPersonas, String nombre,
			String apellido, String dni, int medioPagoID, String emailUsuario) throws ReservaException {
		float montoTotal = 0;
		// valido el formato de las fechas
		LocalDateTime fDesdeConverted;
		LocalDateTime fHastaConverted;
		try {
			fDesdeConverted = convertStringToLocalDateTime(fDesde);
			fHastaConverted = convertStringToLocalDateTime(fHasta);
		} catch (ParseException e) {
			throw new ReservaException(
					"El formato de la fechas ingresadas no es valido. Ejemplo fecha valida: 2018-04-05T12:30-02:00");
		}
		boolean formatoFechaOK = validarFechaReserva(fDesdeConverted, fHastaConverted);
		List<OfertaBloque> bloques = ofertaBloqueDAO.buscarBloquesDePaquetes(ofertaid, fDesdeConverted, fHastaConverted,
				cantPersonas);

		boolean hayDisponibilidad;
		if (bloques == null) {
			hayDisponibilidad = false;
			throw new ReservaException("No se encontraron bloques para la oferta paquete seleccionada");
		} else
			hayDisponibilidad = this.validarDispPaquete(bloques);
		// consulto al backoffice si puedo reservar
		boolean puedoReservar = true
		/* completar despues */;
		// backOfficeAutorizador.getServicioPrestadorAutorizadoPort().getPrestadorAutorizado(1);

		if (!formatoFechaOK)
			throw new ReservaException("La fecha ingresada no tiene el formato adecuado.");
		if (!hayDisponibilidad)
			throw new ReservaException("No hay disponibilidad desde la fecha " + fDesde + " hasta " + fHasta
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
			if (cupoActualizado && hayConsistencia)
				nuevaReservaPaquete = reservaDAO.crearReserva(ofertaid, 1, medioPagoID, nombre, apellido, emailUsuario,
						dni, montoTotal);
			if (!hayConsistencia)
				throw new ReservaException("El id de oferta enviado, no corresponde a una oferta paquete valida.");
			if (!cupoActualizado)
				throw new ReservaException("No se pudo actualizar el cupo en la base de datos.");
			if (nuevaReservaPaquete == null)
				throw new ReservaException("No se puedo grabar la reserva en la base de datos.");
		}
		return mapperService.obtenerReservaDTO(nuevaReservaPaquete);
	}

	public ReservaDTO reservarHotel(int ofertaid, String fDesde, String fHasta, String tipoHabitacion,
			int cantHabitaciones, String nombre, String apellido, String dni, int medioPagoID, String emailUsuario)
			throws ReservaException {
		float montoTotal = 0;
		// valido el formato de las fechas
		LocalDateTime fDesdeConverted;
		LocalDateTime fHastaConverted;
		try {
			fDesdeConverted = convertStringToLocalDateTime(fDesde);
			fHastaConverted = convertStringToLocalDateTime(fHasta);
		} catch (ParseException e) {
			throw new ReservaException(
					"El formato de la fechas involucradas en la reserva no es el correcto. Ejemplo fecha valida: 2018-04-05T12:30-02:00");
		}
		// valido el formato de las fechas
		boolean formatoFechaOK = validarFechaReserva(fDesdeConverted, fHastaConverted);
		List<OfertaBloque> bloques = ofertaBloqueDAO.buscarBloquesDeHoteleria(ofertaid, fDesdeConverted,
				fHastaConverted, tipoHabitacion);

		boolean hayDisponibilidad;
		if (bloques == null) {
			hayDisponibilidad = false;
			throw new ReservaException("No se encontraron bloques para la oferta hotelera seleccionada");
		} else
			hayDisponibilidad = this.validarDispHabitaciones(bloques, cantHabitaciones);
		// consulto al backoffice si puedo reservar
		boolean puedoReservar = true/* completar despues c backoffice */;

		if (!formatoFechaOK)
			throw new ReservaException("La fecha ingresada no tiene el formato adecuado.");
		if (!hayDisponibilidad)
			throw new ReservaException("No hay disponibilidad desde la fecha " + fDesde + " hasta " + fHasta
					+ " para la cant de habitaciones: " + cantHabitaciones);
		if (!puedoReservar)
			throw new ReservaException("No hay autorizacion del backoffice para reservar");

		Reserva nuevaReservaHotelera = null;
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
			if (cupoActualizado && hayConsistencia)
				nuevaReservaHotelera = reservaDAO.crearReserva(ofertaid, 1, medioPagoID, nombre, apellido, emailUsuario,
						dni, montoTotal);
			if (!cupoActualizado)
				throw new ReservaException("No se pudo actualizar el cupo en la base de datos.");
			if (!hayConsistencia)
				throw new ReservaException("El id de oferta enviado, no corresponde a una oferta hotelera valida.");
			if (nuevaReservaHotelera == null)
				throw new ReservaException("No se puedo grabar la reserva en la base de datos.");
		}
		return mapperService.obtenerReservaDTO(nuevaReservaHotelera);
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

	private LocalDateTime convertStringToLocalDateTime(String stringFecha) throws ParseException {
		// Estamos validando que la fecha tenga el formato correcto
		// ejemplo 2018-06-20T12:30-02:00
		DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
		// ISO_OFFSET_DATE_TIME Date Time with Offset 2011-12-03T10:15:30+01:00'
		LocalDateTime dateTime = LocalDateTime.parse(stringFecha, formatter);
		return dateTime;
	}

	private boolean validarFechaReserva(LocalDateTime fDesde, LocalDateTime fHasta) {
		/*
		 * Estamos validando que fDesde no sea mayor que fHasta y que la fecha actual se
		 * encuentre dentro de dichos rangos
		 */
		LocalDateTime fechaActual = LocalDateTime.now();
		if (fDesde.isBefore(fechaActual) && fHasta.isAfter(fechaActual) && fDesde.isBefore(fHasta))
			return true;
		else
			return false;
	}
}
