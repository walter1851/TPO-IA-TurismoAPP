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

import com.reservas.coreservices.BusquedaServiceInterfaceLocal;
import com.reservas.dao.impl.DestinoDAO;
import com.reservas.dao.impl.OfertaDAO;
import com.reservas.dto.OfertaDTO;
import com.reservas.entities.Destino;

/**
 * Session Bean implementation class BusquedaOfertaPaqueteService
 */
@Stateless
@LocalBean
public class BusquedaService implements BusquedaServiceInterfaceLocal{
	@EJB
	OfertaDAO ofertaDao;
	@EJB
	DestinoDAO destinoDao;
	
	public List<OfertaDTO> buscarOfertaPaquete(String destino,int cantPersonas,String fDesde, String fHasta) throws ParseException {
		Date dDesde=parseFecha(fDesde);
		Date dHasta=parseFecha(fHasta);
		if (validarDestino(destino)) {
			
		}
		
		return null;
	}
	public List<OfertaDTO> buscarOfertaHotelera(String destino,int cantPersonas,String fDesde, String fHasta,String tipoHabitacion) throws ParseException {
		Date dDesde=parseFecha(fDesde);
		Date dHasta=parseFecha(fHasta);
		if (validarDestino(destino)) {
			
		}
		
		return null;
	}
	private boolean validarDestino(String destino){
		//validar destino
		Destino destinoFromDatabase=destinoDao.buscarPorNombre(destino);
		if (destinoFromDatabase == null)
			return false;
		else
			return true;
	}
	private Date parseFecha(String fecha) throws ParseException {
		//ValidarFechas
		//Ejemplo: Wed, 4 Jul 2001 12:08:56 -0700
		DateFormat format = new SimpleDateFormat("EEE, d MMM yyyy", Locale.ENGLISH);
		Date desde = format.parse(fecha);
		return desde;
	}
	
}
