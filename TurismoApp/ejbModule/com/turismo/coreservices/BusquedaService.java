package com.turismo.coreservices;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.turismo.coreservices.BusquedaServiceLocal;
import com.turismo.dao.DestinoDAO;
import com.turismo.dao.OfertaDAO;
import com.turismo.dto.DestinoDTO;
import com.turismo.dto.OfertaDTO;
import com.turismo.entities.Agencia;
import com.turismo.entities.Destino;
import com.turismo.entities.Establecimiento;
import com.turismo.entities.MedioPago;
import com.turismo.entities.Oferta;
import com.turismo.entities.OfertaTipo;

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
	public List<OfertaDTO> buscarOfertaPaquete(String destino,int cantPersonas,String fDesde, String fHasta) throws ParseException {
		List<Oferta> ofertasPaquete=null;
		if (validarBusqueda(fDesde)&&validarBusqueda(fHasta)) {
			ofertasPaquete=ofertaDao.buscarOfertasPaquete(destino, cantPersonas, fDesde, fHasta);
		}
		return mapperService.obtenerListOfertaDTO(ofertasPaquete);
	}
	public List<OfertaDTO> buscarOfertaHotelera(String destino,int cantPersonas,String fDesde, String fHasta,String tipoHabitacion) throws ParseException {
		List<Oferta> ofertasHotelera=null;
		if (validarBusqueda(fDesde)&&validarBusqueda(fHasta)) {
			ofertasHotelera=ofertaDao.buscarOfertasPaquete(destino, cantPersonas, fDesde, fHasta);
		}
		return mapperService.obtenerListOfertaDTO(ofertasHotelera);
	}
	private Boolean validarBusqueda(String fecha) throws ParseException {
		//ValidarFechas
		//Ejemplo: Wed, 4 Jul 2001 12:08:56 -0700
		DateFormat format = new SimpleDateFormat("EEE, d MMM yyyy", Locale.ENGLISH);
		Date desde = format.parse(fecha);
		return true;
	}
	

}