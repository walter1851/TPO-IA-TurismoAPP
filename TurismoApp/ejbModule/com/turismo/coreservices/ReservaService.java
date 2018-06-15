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
import com.turismo.entities.OfertaBloque;
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
	private BackOfficeAutorizacion backOfficeAutorizador;

	public boolean reservarPaquete(int ofertaid, String fDesde, String fHasta, int cantPersonas, String nombre,
			String apellido, String dni, String medioPago, String emailUsuario) throws ReservaException {
		// valido el formato de las fechas
		LocalDateTime fDesdeConverted;
		LocalDateTime fHastaConverted;
		try {
			fDesdeConverted = convertStringToLocalDateTime(fDesde);
			fHastaConverted = convertStringToLocalDateTime(fHasta);
		} catch (ParseException e) {
			throw new ReservaException("El formato de la fechas involucradas en la reserva no es el correcto");
		}
		boolean reservaOK = false;
		boolean formatoFechaOK = validarFechaReserva(fDesdeConverted, fHastaConverted);
		// obtengo todos los bloques que coinciden para validar la disponiblidad
		List<OfertaBloque> bloques = ofertaBloqueDAO.buscarBloques(ofertaid, fDesdeConverted, fHastaConverted, cantPersonas);
		boolean hayDisponibilidad = this.validarDisponibilidad(bloques);
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

		if (formatoFechaOK && hayDisponibilidad && puedoReservar) {
			boolean cupoActualizado = true;
			for (OfertaBloque ofertaBloque : bloques) {
				// Descontamos uno al cupo
				ofertaBloque.setCupo(ofertaBloque.getCupo() - 1);
				if (!ofertaBloqueDAO.actualizarBloque(ofertaBloque))
					cupoActualizado = false;
			}
			if (cupoActualizado)
				reservaOK = reservaDAO.crearReserva(ofertaid, 1, medioPago, nombre, emailUsuario, dni);
			else
				throw new ReservaException("No se pudo actualizar el cupo.");
		}
		return reservaOK;
	}

	public boolean reservarHotel(int ofertaid, String fDesde, String fHasta, String tipoHabitacion, int cantPersonas,
			String nombre, String apellido, String dni, String medioPago, String emailUsuario) throws ReservaException {
		// valido el formato de las fechas
		LocalDateTime fDesdeConverted;
		LocalDateTime fHastaConverted;
		try {
			fDesdeConverted = convertStringToLocalDateTime(fDesde);
			fHastaConverted = convertStringToLocalDateTime(fHasta);
		} catch (ParseException e) {
			throw new ReservaException("El formato de la fechas involucradas en la reserva no es el correcto");
		}
		boolean reservaOK = false;
		// valido el formato de las fechas
		boolean formatoFechaOK = validarFechaReserva(fDesdeConverted, fHastaConverted);
		// obtengo todos los bloques que coinciden para validar la disponiblidad
		List<OfertaBloque> bloques = ofertaBloqueDAO.buscarBloques(ofertaid, fDesdeConverted, fHastaConverted, cantPersonas,
				tipoHabitacion);
		boolean hayDisponibilidad = this.validarDisponibilidad(bloques);
		// consulto al backoffice si puedo reservar
		boolean puedoReservar = true/* completar despues */;

		if (!formatoFechaOK)
			throw new ReservaException("La fecha ingresada no tiene el formato adecuado.");
		if (!hayDisponibilidad)
			throw new ReservaException("No hay disponibilidad desde la fecha " + fDesde + " hasta " + fHasta
					+ " para la cantidad de " + cantPersonas + " persona/s");
		if (!puedoReservar)
			throw new ReservaException("No hay autorizacion del backoffice para reservar");

		if (formatoFechaOK && hayDisponibilidad && puedoReservar) {
			boolean cupoActualizado = true;
			for (OfertaBloque ofertaBloque : bloques) {
				// Descontamos uno al cupo
				ofertaBloque.setCupo(ofertaBloque.getCupo() - 1);
				if (!ofertaBloqueDAO.actualizarBloque(ofertaBloque))
					cupoActualizado = false;
			}
			if (cupoActualizado)
				reservaOK = reservaDAO.crearReserva(ofertaid, 1, medioPago, nombre, emailUsuario, dni);
			else
				throw new ReservaException("No se pudo actualizar el cupo.");
		}
		return reservaOK;
	}

	private boolean validarDisponibilidad(List<OfertaBloque> bloques) {
		// Verfico que exista cupo mayor o igual a uno para cada uno de los bloques
		if (bloques.isEmpty())
			return false;
		else {
			boolean disponibilidad = true;
			for (OfertaBloque ofertaBloque : bloques) {
				if (ofertaBloque.getCupo() == 0) {
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
		if (fDesde.isAfter(fechaActual) && fHasta.isBefore(fechaActual) && fDesde.isBefore(fHasta))
			return true;
		else
			return false;
	}
}
