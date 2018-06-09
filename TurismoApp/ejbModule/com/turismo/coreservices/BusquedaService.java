package com.turismo.coreservices;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class BusquedaService implements BusquedaServiceLocal{
	@EJB
	private OfertaDAO ofertaDao;
	@EJB
	private MapperService mapperService;
	public List<OfertaDTO> buscarOfertaPaquete(String destino,int cantPersonas,String fDesde, String fHasta) throws OfertaPaqueteException{
		try {
		List<Oferta> ofertasPaquete=null;
		if (validarBusqueda(fDesde)&&validarBusqueda(fHasta)) {
			ofertasPaquete=ofertaDao.buscarOfertasPaquete(destino, cantPersonas, fDesde, fHasta);
		}
		return mapperService.obtenerListOfertaDTO(ofertasPaquete);
		}catch(ParseException e) {
			throw new OfertaPaqueteException("La fecha ingresada no tiene el formato adecuado.");
		}
	}
	public List<OfertaDTO> buscarOfertaHotelera(String destino,int cantPersonas,String fDesde, String fHasta,String tipoHabitacion) throws OfertaHoteleraException {
		List<Oferta> ofertasHotelera=null;
		try {
			if (validarBusqueda(fDesde)&&validarBusqueda(fHasta)) {
				ofertasHotelera=ofertaDao.buscarOfertasPaquete(destino, cantPersonas, fDesde, fHasta);
			}
		} catch (ParseException e) {
			throw new OfertaHoteleraException("La fecha ingresada no tiene el formato adecuado.");
		}
		return mapperService.obtenerListOfertaDTO(ofertasHotelera);
	}
	private Boolean validarBusqueda(String fecha) throws ParseException {
		//ValidarFechas
		//Ejemplo: Wed, 4 Jul 2001 12:08:56 -0700
		DateFormat format = new SimpleDateFormat("EEE, d MMM yyyy", Locale.ENGLISH);
		@SuppressWarnings("unused")
		Date desde = format.parse(fecha);
		return true;
	}
	

}
