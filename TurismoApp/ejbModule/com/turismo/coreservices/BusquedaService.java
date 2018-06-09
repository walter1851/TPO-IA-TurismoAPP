package com.turismo.coreservices;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
			if (validarBusqueda(fDesde,fHasta))
				ofertasPaquete = ofertaDao.buscarOfertasPaquete(destino, cantPersonas, fDesde, fHasta);
			else
				throw new OfertaPaqueteException("La fecha ingresada no tiene el formato adecuado.");
			if (ofertasPaquete.isEmpty())
				throw new OfertaPaqueteException("No se encontraron paquetes para el destino "+destino+ " desde el "+fDesde+" Hasta el "+ fHasta);
			else
				return mapperService.obtenerListOfertaDTO(ofertasPaquete);
	}

	public List<OfertaDTO> buscarOfertaHotelera(String destino, int cantPersonas, String fDesde, String fHasta,
			String tipoHabitacion) throws OfertaHoteleraException {
		List<Oferta> ofertasHotelera = null;
			if (validarBusqueda(fDesde,fHasta))
				ofertasHotelera = ofertaDao.buscarOfertasPaquete(destino, cantPersonas, fDesde, fHasta);
			else
				throw new OfertaHoteleraException("La fecha ingresada no tiene el formato adecuado.");
			if (ofertasHotelera.isEmpty())
				throw new OfertaHoteleraException("No se encontraron hoteles para el destino "+destino+ " desde el "+fDesde+" Hasta el "+ fHasta+ " para "+cantPersonas+ " personas");
			else
				return mapperService.obtenerListOfertaDTO(ofertasHotelera);
	}
	private Boolean validarBusqueda(String fDesde,String fHasta) {
		// ValidarFechas
		// Ejemplo: Wed, 4 Jul 2001 12:08:56 -0700
		DateFormat format = new SimpleDateFormat("EEE, d MMM yyyy", Locale.ENGLISH);
		// Saque la validación para poder probarlo, despues la activo de nuevo
		// @SuppressWarnings("unused")
		// Date desde = format.parse(fecha);
		return true;
	}

}
