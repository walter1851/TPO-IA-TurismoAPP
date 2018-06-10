package com.turismo.coreservices;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.turismo.coreservices.BusquedaServiceLocal;
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
public class BusquedaService implements BusquedaServiceLocal {
	@EJB
	private OfertaDAO ofertaDao;
	@EJB
	private MapperService mapperService;

	public List<OfertaDTO> buscarOfertaPaquete(String destino, int cantPersonas, String fDesde, String fHasta)
			throws OfertaPaqueteException {
		List<Oferta> ofertasPaquete = null;
		Date fDesdeConverted;
		Date fHastaConverted;
		try {
			fDesdeConverted = convertToDate(fDesde);
			fHastaConverted = convertToDate(fHasta);
		} catch (ParseException e) {
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
			return mapperService.obtenerListOfertaDTO(ofertasPaquete);
	}

	public List<OfertaDTO> buscarOfertaHotelera(String destino, int cantPersonas, String fDesde, String fHasta,
			String tipoHabitacion) throws OfertaHoteleraException {
		List<Oferta> ofertasPaquete = null;
		Date fDesdeConverted;
		Date fHastaConverted;
		try {
			fDesdeConverted = convertToDate(fDesde);
			fHastaConverted = convertToDate(fHasta);
		} catch (ParseException e) {
			throw new OfertaHoteleraException("El formato de la fecha no es el correcto");
		}
		if (validarBusqueda(fDesdeConverted, fHastaConverted))
			ofertasPaquete = ofertaDao.buscarOfertasHotelera(destino, cantPersonas, fDesdeConverted, fHastaConverted);
		else
			throw new OfertaHoteleraException("La fecha de inicio es mayor que la final o la fecha actual no se encuentra dentro de dicho rangos");
		
		if (ofertasPaquete.isEmpty())
			throw new OfertaHoteleraException("No se encontraron paquetes para el destino " + destino + " desde el "
					+ fDesde + " Hasta el " + fHasta);
		else
			return mapperService.obtenerListOfertaDTO(ofertasPaquete);
	}

	private Boolean validarBusqueda(Date fDesde, Date fHasta) {
		/*
		 * Estamos validando que fDesde no sea mayor que fHasta y que la fecha actual se
		 * encuentre dentro de dichos rangos
		 */
		boolean fHastaMayor = fDesde.before(fHasta);
		Date fechaActual = Calendar.getInstance().getTime();
		if (fDesde.after(fechaActual) && fHasta.before(fechaActual) && fHastaMayor)
			return true;
		else
			return false;
	}

	private Date convertToDate(String stringFecha) throws ParseException {
		//Estamos validando que la fecha tenga el formato correcto
		DateFormat format = new SimpleDateFormat("EEE, d MMM yyyy", Locale.ENGLISH);
		Date date = format.parse(stringFecha);
		return date;
	}
}
