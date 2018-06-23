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
import com.turismo.exceptions.ReservaException;

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

	public float calcularPrecioTotalHotel(int ofertaId, int cantidadHabitaciones, String fDesde, String fHasta)
			throws OfertaHoteleraException, ConversionFechaException, OfertaPaqueteException {
		float montoTotal = -1;
		LocalDate fDesdeConverted = this.convertStringToLocalDate(fDesde);
		LocalDate fHastaConverted = this.convertStringToLocalDate(fDesde);
		if (validarRangoFechaHotelera(fDesdeConverted, fHastaConverted)) {
			int cantDiasHotel = fHastaConverted.compareTo(fDesdeConverted);
			if (existeOfertaHotelera(ofertaId))
				montoTotal = ofertaDAO.buscarPorIdOferta(ofertaId).getPrecio() * cantidadHabitaciones * cantDiasHotel;
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
		if (validarRangoFechaPaquete(fDesdeConverted, fHastaConverted))
			ofertasPaquete = ofertaDAO.buscarOfertasPaquete(codigoDestino, cantPersonas, fDesdeConverted,
					fHastaConverted);

		if (ofertasPaquete != null && ofertasPaquete.isEmpty())
			throw new OfertaPaqueteException(
					"No se encontraron paquetes para el destino id: " + codigoDestino + " desde el " + fDesdeConverted
							+ " Hasta el " + fHastaConverted + " cant personas: " + cantPersonas);
		else
			return mapperService.obtenerListaOfertaPaqueteDTO(ofertasPaquete);
	}

	public List<OfertaDTO> buscarOtrosPaquetesMismoDestino(int codigo_paquete_a_excluir, int codigo_destino,
			int cantPersonas, String fDesdeString, String fHastaString)
			throws ConversionFechaException, OfertaPaqueteException {
		List<Oferta> otrosPaquetesMismoDestino = null;
		LocalDate fDesdeConverted = convertStringToLocalDate(fDesdeString);
		LocalDate fHastaConverted = convertStringToLocalDate(fHastaString);
		if (validarRangoFechaPaquete(fDesdeConverted, fHastaConverted)) {
			otrosPaquetesMismoDestino = ofertaDAO.buscarOtrosPaquetesMismoDestino(codigo_paquete_a_excluir,
					codigo_destino, cantPersonas, fDesdeConverted, fHastaConverted);
		}
		if (otrosPaquetesMismoDestino != null && otrosPaquetesMismoDestino.isEmpty())
			throw new OfertaPaqueteException(
					"No se encontraron paquetes para mismo destino. Codigo destino " + codigo_destino + " desde el "
							+ fDesdeConverted + " Hasta el " + fHastaConverted + " cant personas: " + cantPersonas);
		else
			return mapperService.obtenerListaOfertaPaqueteDTO(otrosPaquetesMismoDestino);
	}

	public List<OfertaDTO> buscarOfertaHotelera(int codigoDestino, String fDesde, String fHasta, String tipoHabitacion)
			throws OfertaHoteleraException, ConversionFechaException {
		LocalDate fDesdeConverted = convertStringToLocalDate(fDesde);
		LocalDate fHastaConverted = convertStringToLocalDate(fHasta);
		List<Oferta> ofertasHoteleras = null;
		if (validarRangoFechaHotelera(fDesdeConverted, fHastaConverted))
			ofertasHoteleras = ofertaDAO.buscarOfertasHotelera(codigoDestino, tipoHabitacion, fDesdeConverted,
					fHastaConverted);

		if (ofertasHoteleras != null && ofertasHoteleras.isEmpty())
			throw new OfertaHoteleraException("No se encontraron hoteles para el destino id: " + codigoDestino
					+ " desde el " + fDesde + " Hasta el " + fHasta + " tipo habitacion: " + tipoHabitacion);
		else
			return mapperService.obtenerListaOfertaHoteleraDTO(ofertasHoteleras);
	}

	public List<OfertaDTO> buscarOtrasOfertasMismoHotel(int codigo_destino, String tipo_Habitacion_a_excluir,
			int codigo_Hotel, String fDesde, String fHasta) throws ConversionFechaException, OfertaHoteleraException {
		List<Oferta> ofertasHoteleras = null;
		LocalDate fDesdeConverted = convertStringToLocalDate(fDesde);
		LocalDate fHastaConverted = convertStringToLocalDate(fHasta);
		if (validarRangoFechaHotelera(fDesdeConverted, fHastaConverted))
			ofertasHoteleras = ofertaDAO.buscarOtrasOfertasMismoHotel(codigo_destino, tipo_Habitacion_a_excluir,
					codigo_Hotel, fDesdeConverted, fHastaConverted);
		if (ofertasHoteleras != null && ofertasHoteleras.isEmpty())
			throw new OfertaHoteleraException("No se encontraron otras ofertas para el mismo hotel.");
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
					"La fechas ingresadas no se encuentran dentro del rango esperado. Verifique que la fecha de inicio "
							+ localDateDesde.toString() + " es menor o igual que la fecha de salida "
							+ localDateHasta.toString() + " , y que dicho rango sea posterior a la fecha actual "
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
		if (localDateHasta.compareTo(localDateFechaActual) >= 0 && localDateHasta.compareTo(localDateDesde) >= 0)
			rangoValido = true;
		else
			rangoValido = false;
		if (!rangoValido)
			throw new OfertaHoteleraException(
					"La fechas ingresadas no se encuentran dentro del rango esperado. Verifique que la fecha de inicio "
							+ localDateDesde.toString() + " es menor o igual que la fecha de salida "
							+ localDateHasta.toString() + " , y que dicho rango se encuentre dentro de la fecha actual "
							+ LocalDate.now().toString());
		return rangoValido;
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
