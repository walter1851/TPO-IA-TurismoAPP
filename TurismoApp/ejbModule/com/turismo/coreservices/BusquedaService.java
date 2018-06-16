package com.turismo.coreservices;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.turismo.dao.OfertaDAO;
import com.turismo.dto.OfertaDTO;
import com.turismo.entities.Oferta;
import com.turismo.exceptions.OfertaHoteleraException;
import com.turismo.exceptions.OfertaPaqueteException;

/**
 * Session Bean implementation class BusquedaOfertaPaqueteService
 */
@Stateless
@LocalBean
public class BusquedaService{
	@EJB
	private OfertaDAO ofertaDao;
	@EJB
	private MapperService mapperService;

	public List<OfertaDTO> buscarOfertaPaquete(String destino, int cantPersonas, String fDesde, String fHasta)
			throws OfertaPaqueteException {
		List<Oferta> ofertasPaquete = null;
		LocalDateTime fDesdeConverted;
		LocalDateTime fHastaConverted;
		try {
			fDesdeConverted = convertStringToLocalDateTime(fDesde);
			fHastaConverted = convertStringToLocalDateTime(fHasta);
		} catch (DateTimeParseException e) {
			throw new OfertaPaqueteException("El formato de la fecha no es el correcto");
		}
		if (validarBusqueda(fDesdeConverted, fHastaConverted))
			ofertasPaquete = ofertaDao.buscarOfertasPaquete(destino, cantPersonas, fDesdeConverted, fHastaConverted);
		else
			throw new OfertaPaqueteException("La fecha de inicio es mayor que la final o la fecha actual no se encuentra dentro de dicho rangos");
		
		if (ofertasPaquete.isEmpty())
			throw new OfertaPaqueteException("No se encontraron paquetes para el destino " + destino + " desde el "
					+ fDesde + " Hasta el " + fHasta);
		else
			return mapperService.obtenerListOfertaPaqueteDTO(ofertasPaquete);
	}

	public List<OfertaDTO> buscarOfertaHotelera(String destino, int cantPersonas, String fDesde, String fHasta,
			String tipoHabitacion) throws OfertaHoteleraException {
		List<Oferta> ofertasHoteleras = null;
		LocalDateTime fDesdeConverted;
		LocalDateTime fHastaConverted;
		try {
			fDesdeConverted = convertStringToLocalDateTime(fDesde);
			fHastaConverted = convertStringToLocalDateTime(fHasta);
		} catch (DateTimeParseException e) {
			throw new OfertaHoteleraException("El formato de la fecha no es el correcto");
		}
		if (validarBusqueda(fDesdeConverted, fHastaConverted))
			ofertasHoteleras = ofertaDao.buscarOfertasHotelera(destino, cantPersonas, fDesdeConverted, fHastaConverted);
		else
			throw new OfertaHoteleraException("La fecha de inicio es mayor que la final o la fecha actual no se encuentra dentro de dicho rangos");
		
		if (ofertasHoteleras.isEmpty())
			throw new OfertaHoteleraException("No se encontraron paquetes para el destino " + destino + " desde el "
					+ fDesde + " Hasta el " + fHasta);
		else
			return mapperService.obtenerListOfertaHoteleraDTO(ofertasHoteleras);
	}

	private Boolean validarBusqueda(LocalDateTime fDesde, LocalDateTime fHasta) {
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
	private LocalDateTime convertStringToLocalDateTime(String stringFecha) {
		//Estamos validando que la fecha tenga el formato correcto
		//ejemplo 2018-06-20T12:30-02:00
		DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
		//ISO_OFFSET_DATE_TIME	Date Time with Offset	2011-12-03T10:15:30+01:00'
		LocalDateTime dateTime = LocalDateTime.parse(stringFecha, formatter);
		return dateTime;
	}
}
