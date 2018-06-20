package com.turismo.coreservices;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.turismo.dao.OfertaDAO;
import com.turismo.dto.OfertaDTO;
import com.turismo.entities.Oferta;
import com.turismo.entities.OfertaTipo;
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

	public boolean existeOfertaHotelera(int ofertaId) throws OfertaHoteleraException {
		Oferta ofertaExistente = ofertaDAO.buscarPorIdOferta(ofertaId);
		boolean existeIdOferta;
		boolean esOfertaHotelera;

		if (ofertaExistente == null) {
			existeIdOferta=false;
			throw new OfertaHoteleraException("la oferta id " + ofertaId + " no existe en la base de datos.");
		}
		else {
			existeIdOferta=true;
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
			existeIdOferta=false;
			throw new OfertaPaqueteException("la oferta id " + ofertaId + " no existe en la base de datos.");
		}
		else {
			existeIdOferta=true;
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
					"Se produjo un error grave en el calculo de monto total del paquete. Dicho valor es menor a cero.");
		return montoTotal;
	}

	public float calcularPrecioTotalHotel(int ofertaId, int cantidadHabitaciones, String fDesde, String fHasta)
			throws OfertaHoteleraException, ConversionFechaException {
		LocalDate fDesdeConverted = this.convertStringToLocalDate(fDesde);
		LocalDate fHastaConverted = this.convertStringToLocalDate(fDesde);
		int cantDiasHotel = fHastaConverted.compareTo(fDesdeConverted);
		float montoTotal = -1;
		if (existeOfertaHotelera(ofertaId))
			montoTotal = ofertaDAO.buscarPorIdOferta(ofertaId).getPrecio() * cantidadHabitaciones * cantDiasHotel;
		if (montoTotal < 0)
			throw new OfertaHoteleraException(
					"Se produjo un error grave en el calculo del hospedaje. El monto total es menor a cero ");
		return montoTotal;
	}

	public List<OfertaDTO> buscarOtrosPaquetesMismoDestino(int codigo_paquete_a_excluir, int codigo_destino,
			int cantPersonas, String fDesdeString, String fHastaString) throws ConversionFechaException {
		LocalDate fDesdeConverted = convertStringToLocalDate(fDesdeString);
		LocalDate fHastaConverted = convertStringToLocalDate(fHastaString);
		List<Oferta> otrosPaquetesMismoDestino = ofertaDAO.buscarOtrosPaquetesMismoDestino(codigo_paquete_a_excluir,
				codigo_destino, cantPersonas, fDesdeConverted, fHastaConverted);
		return mapperService.obtenerListaOfertaPaqueteDTO(otrosPaquetesMismoDestino);
	}

	public List<OfertaDTO> buscarOfertaPaquete(int codigoDestino, int cantPersonas, String fDesdeString,
			String fHastaString) throws OfertaPaqueteException, ConversionFechaException {
		LocalDate fDesdeConverted = convertStringToLocalDate(fDesdeString);
		LocalDate fHastaConverted = convertStringToLocalDate(fHastaString);

		List<Oferta> ofertasPaquete;
		if (validarRangoFechaPaquete(fDesdeConverted, fHastaConverted))
			ofertasPaquete = ofertaDAO.buscarOfertasPaquete(codigoDestino, cantPersonas, fDesdeConverted,
					fHastaConverted);
		else
			throw new OfertaPaqueteException(
					"La fecha de inicio es mayor que la final o la fecha actual no se encuentra dentro de dicho rangos");

		if (ofertasPaquete.isEmpty())
			throw new OfertaPaqueteException(
					"No se encontraron paquetes para el destino id: " + codigoDestino + " desde el " + fDesdeConverted
							+ " Hasta el " + fHastaConverted + " cant personas: " + cantPersonas);
		else
			return mapperService.obtenerListaOfertaPaqueteDTO(ofertasPaquete);
	}

	public List<OfertaDTO> buscarOtrasOfertasMismoHotel(int codigo_destino, String tipo_Habitacion_a_excluir,
			int codigo_Hotel, String fDesde, String fHasta) throws ConversionFechaException {
		LocalDate fDesdeConverted = convertStringToLocalDate(fDesde);
		LocalDate fHastaConverted = convertStringToLocalDate(fHasta);
		List<Oferta> ofertasHoteleras = ofertaDAO.buscarOtrasOfertasMismoHotel(codigo_destino,
				tipo_Habitacion_a_excluir, codigo_Hotel, fDesdeConverted, fHastaConverted);
		return mapperService.obtenerListaOfertaPaqueteDTO(ofertasHoteleras);
	}

	public List<OfertaDTO> buscarOfertaHotelera(int codigoDestino, String fDesde, String fHasta, String tipoHabitacion)
			throws OfertaHoteleraException, ConversionFechaException {
		LocalDate fDesdeConverted = convertStringToLocalDate(fDesde);
		LocalDate fHastaConverted = convertStringToLocalDate(fHasta);

		List<Oferta> ofertasHoteleras;
		if (validarRangoFechaHotelera(fDesdeConverted, fHastaConverted))
			ofertasHoteleras = ofertaDAO.buscarOfertasHotelera(codigoDestino, tipoHabitacion, fDesdeConverted,
					fHastaConverted);
		else
			throw new OfertaHoteleraException(
					"La fecha de inicio es mayor que la final o la fecha actual no se encuentra dentro de dicho rangos");

		if (ofertasHoteleras.isEmpty())
			throw new OfertaHoteleraException("No se encontraron hoteles para el destino id: " + codigoDestino
					+ " desde el " + fDesde + " Hasta el " + fHasta + " tipo habitacion: " + tipoHabitacion);
		else
			return mapperService.obtenerListaOfertaHoteleraDTO(ofertasHoteleras);
	}

	public Boolean validarRangoFechaPaquete(LocalDate localDateDesde, LocalDate localDateHasta) {
		LocalDate localDateFechaActual = LocalDate.now();
		if (localDateDesde.compareTo(localDateFechaActual) >= 0 && localDateHasta.compareTo(localDateFechaActual) >= 0
				&& localDateHasta.compareTo(localDateDesde) >= 0)
			return true;
		else
			return false;
	}

	public Boolean validarRangoFechaHotelera(LocalDate localDateDesde, LocalDate localDateHasta) {
		/*
		 * Estamos validando que fDesde no sea mayor que fHasta y que la fecha actual se
		 * encuentre dentro de dichos rangos
		 */
		LocalDate localDateFechaActual = LocalDate.now();
		if (localDateHasta.compareTo(localDateFechaActual) >= 0 && localDateHasta.compareTo(localDateDesde) >= 0)
			return true;
		else
			return false;
	}

	public LocalDate convertStringToLocalDate(String stringFecha) throws ConversionFechaException {
		// Estamos validando que la fecha tenga el formato correcto
		// ejemplo 2018-06-20T12:30-02:00
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
			// ISO_OFFSET_DATE_TIME Date Time with Offset 2011-12-03T10:15:30+01:00'
			LocalDate localDate = LocalDate.parse(stringFecha, formatter);
			return localDate;
		} catch (DateTimeParseException e) {
			throw new ConversionFechaException(
					"El formato de la fechas ingresadas no es valido. Ejemplo fecha valida: 2018-04-05T12:30-02:00");
		}
	}
}
