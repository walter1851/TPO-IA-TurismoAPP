package com.reservas.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.reservas.dao.impl.DestinoDAO;
import com.reservas.dao.impl.OfertaDAO;
import com.reservas.dto.OfertaDTO;
import com.reservas.entities.Destino;
import com.reservas.entities.Oferta;
import com.reservas.services.BusquedaServiceInterfaceLocal;

/**
 * Session Bean implementation class BusquedaOfertaPaqueteService
 */
@Stateless
@LocalBean
public class BusquedaService implements BusquedaServiceInterfaceLocal{
	@EJB
	OfertaDAO ofertaDao;
	@EJB
	MapperService mapperService;

	public List<OfertaDTO> buscarOfertaPaquete(String destino,int cantPersonas,String fDesde, String fHasta) throws ParseException {
		List<Oferta> ofertasPaquete=null;
		if (validarBusqueda(fDesde)&&validarBusqueda(fHasta)) {
			ofertasPaquete=ofertaDao.buscarOfertasPaquete(destino, cantPersonas, fDesde, fHasta);
		}
		return mapperService.mapEntityToDto(ofertasPaquete);
	}
	public List<OfertaDTO> buscarOfertaHotelera(String destino,int cantPersonas,String fDesde, String fHasta,String tipoHabitacion) throws ParseException {
		List<Oferta> ofertasHotelera=null;
		if (validarBusqueda(fDesde)&&validarBusqueda(fHasta)) {
			ofertasHotelera=ofertaDao.buscarOfertasPaquete(destino, cantPersonas, fDesde, fHasta);
		}
		return mapperService.mapEntityToDto(ofertasHotelera);
	}
	private Boolean validarBusqueda(String fecha) throws ParseException {
		//ValidarFechas
		//Ejemplo: Wed, 4 Jul 2001 12:08:56 -0700
		DateFormat format = new SimpleDateFormat("EEE, d MMM yyyy", Locale.ENGLISH);
		Date desde = format.parse(fecha);
		return true;
	}
	
}
