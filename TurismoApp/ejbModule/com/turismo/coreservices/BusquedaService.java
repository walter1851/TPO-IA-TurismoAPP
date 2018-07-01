package com.turismo.coreservices;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.turismo.dao.OfertaBloqueDAO;
import com.turismo.dao.OfertaDAO;
import com.turismo.dto.OfertaDTO;
import com.turismo.entities.Oferta;
import com.turismo.entities.OfertaBloque;
import com.turismo.entities.OfertaTipo;
import com.turismo.entities.TipoHabitacion;
import com.turismo.exceptions.ConversionFechaException;
import com.turismo.exceptions.OfertaHoteleraException;
import com.turismo.exceptions.OfertaPaqueteException;

/**
 * Session Bean implementation class BusquedaOfertaPaqueteService
 */
@Stateless
@LocalBean
public class BusquedaService {
	@EJB
	private OfertaDAO ofertaDAO;
	@EJB
	private MapperService mapperService;
	@EJB
	private OfertaBloqueDAO ofertaBloqueDAO;

	public boolean existeOfertaHotelera(int ofertaId) throws OfertaHoteleraException {
		Oferta ofertaExistente = ofertaDAO.buscarPorIdOferta(ofertaId);
		boolean existeIdOferta;
		boolean esOfertaHotelera;

		if (ofertaExistente == null) {
			existeIdOferta = false;
			throw new OfertaHoteleraException("la oferta id " + ofertaId + " no existe en la base de datos.");
		} else {
			existeIdOferta = true;
			esOfertaHotelera = ofertaExistente.getOfertaTipo().equals(OfertaTipo.OFERTA_HOTELERA);
			if (!esOfertaHotelera)
				throw new OfertaHoteleraException("la oferta id " + ofertaId
						+ " existe en la base de datos, pero no corresponde a una oferta de tipo hotelera.");
		}
		return (existeIdOferta && esOfertaHotelera);
	}

	public boolean existeOfertaPaquete(int ofertaId) throws OfertaPaqueteException {
		Oferta ofertaExistente = ofertaDAO.buscarPorIdOferta(ofertaId);
		boolean existeIdOferta;
		boolean esOfertaPaquete;

		if (ofertaExistente == null) {
			existeIdOferta = false;
			throw new OfertaPaqueteException("la oferta id " + ofertaId + " no existe en la base de datos.");
		} else {
			existeIdOferta = true;
			esOfertaPaquete = ofertaExistente.getOfertaTipo().equals(OfertaTipo.OFERTA_PAQUETE);
			if (!esOfertaPaquete)
				throw new OfertaPaqueteException("la oferta id " + ofertaId
						+ " existe en la base de datos, pero no corresponde a una oferta de tipo paquete.");
		}
		return (existeIdOferta && esOfertaPaquete);
	}

	public float calcularPrecioTotalPaquete(int ofertaId, int cantidadPersonas) throws OfertaPaqueteException {
		float montoTotal = -1;
		if (existeOfertaPaquete(ofertaId))
			montoTotal = ofertaDAO.buscarPorIdOferta(ofertaId).getPrecio() * cantidadPersonas;
		if (montoTotal < 0)
			throw new OfertaPaqueteException(
					"Se produjo un error grave en el calculo de monto total del paquete. Se obtuvo un valor menor a cero.");
		return montoTotal;
	}

	public float calcularPrecioTotalHotel(int ofertaId, String tipoHabString, int cantidadTotalPersonas, String fDesde,
			String fHasta) throws OfertaHoteleraException, ConversionFechaException, OfertaPaqueteException {
		float montoTotal = -1;
		LocalDate fDesdeConverted = this.convertStringFReservaToLocalDate(fDesde);
		LocalDate fHastaConverted = this.convertStringFReservaToLocalDate(fHasta);
		int cantTotalHabitaciones = this.calcularTotalHabitaciones(cantidadTotalPersonas,
				TipoHabitacion.valueOf(tipoHabString));
		if (validarRangoFechaHotelera(fDesdeConverted, fHastaConverted)) {
			//Sumamos un dia mas porque sino no lo calcula bien
			int cantDiasHotel = (int) java.time.temporal.ChronoUnit.DAYS.between(fDesdeConverted, fHastaConverted);
			if (existeOfertaHotelera(ofertaId))
				montoTotal = ofertaDAO.buscarPorIdOferta(ofertaId).getPrecio() * cantTotalHabitaciones * cantDiasHotel;
			if (montoTotal < 0)
				throw new OfertaHoteleraException(
						"Se produjo un error grave en el calculo del hospedaje. El monto total es menor a cero ");
		}
		return montoTotal;
	}

	public List<OfertaDTO> buscarOfertaPaquete(int codigoDestino, int cantPersonas, String fDesdeString,
			String fHastaString) throws OfertaPaqueteException, ConversionFechaException {
		LocalDate fDesdeConverted = convertStringToLocalDate(fDesdeString);
		LocalDate fHastaConverted = convertStringToLocalDate(fHastaString);
		List<Oferta> ofertasPaquete = null;
		if (validarRangoFechaPaquete(fDesdeConverted, fHastaConverted)) {
			ofertasPaquete = ofertaDAO.buscarOfertasPaquete(codigoDestino, cantPersonas, fDesdeConverted,
					fHastaConverted);
			boolean hayDisponibilidad;
			for (Oferta oferta : ofertasPaquete) {
				List<OfertaBloque> bloques = ofertaBloqueDAO.buscarBloquesDePaquetes(oferta.getOferta_id(),
						fDesdeConverted, fHastaConverted, cantPersonas);
				hayDisponibilidad = this.validarDisponibilidadPaquete(bloques);
				if (!hayDisponibilidad)
					ofertasPaquete.remove(oferta);
			}
		}
		if (ofertasPaquete != null && ofertasPaquete.isEmpty())
			throw new OfertaPaqueteException(
					"No se encontraron paquetes para el criterio de busqueda destino id " + codigoDestino + " desde " + fDesdeConverted
							+ " hasta " + fHastaConverted + " cant. personas " + cantPersonas);
		else
			return mapperService.obtenerListaOfertaPaqueteDTO(ofertasPaquete);
	}

	public List<OfertaDTO> buscarOtrosPaquetesMismoDestino(int id_paquete_a_excluir, int codigo_destino,
			int cantPersonas, String fDesdeString, String fHastaString)
			throws ConversionFechaException, OfertaPaqueteException {
		List<Oferta> otrosPaquetesMismoDestino = null;
		LocalDate fDesdeConverted = convertStringToLocalDate(fDesdeString);
		LocalDate fHastaConverted = convertStringToLocalDate(fHastaString);
		if (validarRangoFechaPaquete(fDesdeConverted, fHastaConverted)) {
			otrosPaquetesMismoDestino = ofertaDAO.buscarOtrosPaquetesMismoDestino(id_paquete_a_excluir, codigo_destino,
					cantPersonas, fDesdeConverted, fHastaConverted);
			boolean hayDisponibilidad;
			for (Oferta oferta : otrosPaquetesMismoDestino) {
				List<OfertaBloque> bloques = ofertaBloqueDAO.buscarBloquesDePaquetes(oferta.getOferta_id(),
						fDesdeConverted, fHastaConverted, cantPersonas);
				hayDisponibilidad = this.validarDisponibilidadPaquete(bloques);
				if (!hayDisponibilidad)
					otrosPaquetesMismoDestino.remove(oferta);
			}

		}
		if (otrosPaquetesMismoDestino != null && otrosPaquetesMismoDestino.isEmpty())
			throw new OfertaPaqueteException(
					"No se encontraron paquetes para mismo codigo destino " + codigo_destino + " desde el "
							+ fDesdeConverted + " Hasta el " + fHastaConverted + " cant. personas " + cantPersonas);
		else
			return mapperService.obtenerListaOfertaPaqueteDTO(otrosPaquetesMismoDestino);
	}

	public List<OfertaDTO> buscarOfertaHotelera(int codigoDestino, String fDesde, String fHasta, String tipoHabString,
			int cantTotalPersonas) throws OfertaHoteleraException, ConversionFechaException {
		LocalDate fDesdeConverted = convertStringToLocalDate(fDesde);
		LocalDate fHastaConverted = convertStringToLocalDate(fHasta);
		TipoHabitacion tipoHabitacion = TipoHabitacion.valueOf(tipoHabString);
		List<Oferta> ofertasHoteleras = null;
		if (validarRangoFechaHotelera(fDesdeConverted, fHastaConverted)) {
			ofertasHoteleras = ofertaDAO.buscarOfertasHotelera(codigoDestino, tipoHabitacion, fDesdeConverted,
					fHastaConverted.minusDays(1));
			boolean hayDisponibilidad = false;
			for (Oferta oferta : ofertasHoteleras) {
				//Me traigo todos los bloques menos el ultimo porque la reserva es por cada noche.
				List<OfertaBloque> bloques = ofertaBloqueDAO.buscarBloquesDeHoteleria(oferta.getOferta_id(),
						fDesdeConverted, fHastaConverted.minusDays(1), tipoHabitacion);
				int cantHabitaciones = calcularTotalHabitaciones(cantTotalPersonas, tipoHabitacion);
				hayDisponibilidad = this.validarDisponibilidadHotelera(bloques, cantHabitaciones);
				if (!hayDisponibilidad)
					ofertasHoteleras.remove(oferta);
			}
		}
		if (ofertasHoteleras != null && ofertasHoteleras.isEmpty())
			throw new OfertaHoteleraException(
					"No se encontraron hoteles para el criterio de busqueda destino id " + codigoDestino + " desde " + fDesde
							+ " hasta " + fHasta + " tipo habitacion " + tipoHabitacion.getTipoHabitacion());
		else
			return mapperService.obtenerListaOfertaHoteleraDTO(ofertasHoteleras);
	}

	// Otras habitaciones disponibles del mismo hotel.
	public List<OfertaDTO> buscarOtrasOfertasMismoHotel(int codigo_destino, String tipo_Habitacion_a_excluir,
			int id_hotel, String fDesde, String fHasta, int cantTotalPersonas)
			throws ConversionFechaException, OfertaHoteleraException {
		List<Oferta> ofertasHoteleras = null;
		LocalDate fDesdeConverted = convertStringToLocalDate(fDesde);
		LocalDate fHastaConverted = convertStringToLocalDate(fHasta);
		if (validarRangoFechaHotelera(fDesdeConverted, fHastaConverted)) {
			ofertasHoteleras = ofertaDAO.buscarOtrasOfertasMismoHotel(codigo_destino, tipo_Habitacion_a_excluir,
					id_hotel, fDesdeConverted, fHastaConverted.minusDays(1));
			boolean hayDisponibilidad = false;
			for (Oferta oferta : ofertasHoteleras) {
				List<OfertaBloque> bloques = ofertaBloqueDAO.buscarBloquesDeHoteleria(oferta.getOferta_id(),
						fDesdeConverted, fHastaConverted.minusDays(1), oferta.getTipo_habitacion());
				int cantHabitaciones = calcularTotalHabitaciones(cantTotalPersonas, oferta.getTipo_habitacion());
				hayDisponibilidad = this.validarDisponibilidadHotelera(bloques, cantHabitaciones);
				if (!hayDisponibilidad)
					ofertasHoteleras.remove(oferta);
			}
		}
		if (ofertasHoteleras != null && ofertasHoteleras.isEmpty())
			throw new OfertaHoteleraException("No se encontraron otras habitaciones para el mismo id hotel " + id_hotel
					+ " desde " + fDesde + " hasta " + fHasta + " cantidad total de personas " + cantTotalPersonas);
		else
			return mapperService.obtenerListaOfertaHoteleraDTO(ofertasHoteleras);
	}

	public Boolean validarRangoFechaPaquete(LocalDate localDateDesde, LocalDate localDateHasta)
			throws OfertaPaqueteException {
		LocalDate localDateFechaActual = LocalDate.now();
		boolean rangoValido;
		if (localDateDesde.compareTo(localDateFechaActual) >= 0 && localDateHasta.compareTo(localDateFechaActual) >= 0
				&& localDateHasta.compareTo(localDateDesde) >= 0)
			rangoValido = true;
		else
			rangoValido = false;
		if (!rangoValido)
			throw new OfertaPaqueteException(
					"La fechas ingresadas no se encuentran dentro del rango esperado. Verifique que la fecha de inicio ingresada "
							+ localDateDesde.toString() + " es menor que la fecha de salida ingresada "
							+ localDateHasta.toString() + " y que dicho rango se encuentre dentro de la fecha actual "
							+ LocalDate.now().toString());
		return rangoValido;
	}

	public Boolean validarRangoFechaHotelera(LocalDate localDateDesde, LocalDate localDateHasta)
			throws OfertaHoteleraException {
		/*
		 * Estamos validando que fDesde no sea mayor que fHasta y que la fecha actual se
		 * encuentre dentro de dichos rangos
		 */
		boolean rangoValido;
		LocalDate localDateFechaActual = LocalDate.now();
		if (localDateDesde.compareTo(localDateFechaActual) >= 0 && localDateHasta.compareTo(localDateFechaActual) > 0
				&& localDateHasta.compareTo(localDateDesde) > 0)
			rangoValido = true;
		else
			rangoValido = false;
		if (!rangoValido)
			throw new OfertaHoteleraException(
					"La fechas ingresadas no se encuentran dentro del rango esperado. Verifique que la fecha de inicio ingresada "
							+ localDateDesde.toString() + " es menor que la fecha de salida ingresada "
							+ localDateHasta.toString() + " y que dicho rango se encuentre dentro de la fecha actual "
							+ LocalDate.now().toString());
		return rangoValido;
	}

	public LocalDate convertStringToLocalDate(String stringFecha) throws ConversionFechaException {
		// 2011-12-20T10:15:30+01:00
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
			// ISO_OFFSET_DATE_TIME Date Time with Offset 2011-12-03T10:15:30+01:00'
			LocalDate localDate = LocalDate.parse(stringFecha, formatter);
			return localDate;
		} catch (DateTimeParseException e) {
			throw new ConversionFechaException(
					"El formato de la fechas ingresadas no es valido. Ejemplo fecha valida: 2018-04-20T12:30-02:00");
		}
	}
	public LocalDate convertStringFReservaToLocalDate(String stringFechaReserva) throws ConversionFechaException {
		try {
			// Ejemplo 2018-06-20
			DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
			LocalDate localDate = LocalDate.parse(stringFechaReserva, formatter);
			return localDate;
		} catch (DateTimeParseException e) {
			throw new ConversionFechaException(
					"El formato de la fechas ingresadas no es valido. Ejemplo fecha valida: 2018-12-05");
		}
	}
	public boolean validarDisponibilidadHotelera(List<OfertaBloque> bloques, int cantHabitaciones)
			throws OfertaHoteleraException {
		if (bloques.isEmpty())
			return false;
		else {
			boolean disponibilidad = true;
			for (OfertaBloque ofertaBloque : bloques) {
				if ((ofertaBloque.getCupo() - (cantHabitaciones * 1)) < 0) {
					disponibilidad = false;
				}
			}
			if (!disponibilidad)
				throw new OfertaHoteleraException("No hay cupo disponible para la oferta hotelera");
			return disponibilidad;
		}
	}

	public int calcularTotalHabitaciones(int cantTotalPersonas, TipoHabitacion tipoHabitacion) {
		float calculoAuxiliar = (float) cantTotalPersonas / tipoHabitacion.getMaxCantPersonas();
		int cantHabitaciones = (int) Math.ceil(calculoAuxiliar);
		return cantHabitaciones;
	}

	public boolean validarDisponibilidadPaquete(List<OfertaBloque> bloques) throws OfertaPaqueteException {
		if (bloques.isEmpty())
			return false;
		else {
			boolean disponibilidad = true;
			for (OfertaBloque ofertaBloque : bloques) {
				if ((ofertaBloque.getCupo() - 1) < 0) {
					disponibilidad = false;
				}
			}
			if (!disponibilidad)
				throw new OfertaPaqueteException("No hay cupo disponible para la oferta paquete");
			return disponibilidad;
		}
	}
}
