package com.turismo.coreservices;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.turismo.dao.OfertaDAO;
import com.turismo.dto.OfertaDTO;
import com.turismo.entities.Oferta;
import com.turismo.exceptions.ConversionFechaException;
import com.turismo.exceptions.OfertaHoteleraException;
import com.turismo.exceptions.OfertaPaqueteException;
import com.turismo.exceptions.ReservaException;

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
	
	public List<OfertaDTO> buscarOfertaPaquete(int destinoId, int cantPersonas, String fDesdeString, String fHastaString)
			throws OfertaPaqueteException, ConversionFechaException {
		List<Oferta> ofertasPaquete;
		LocalDate fDesdeConverted;
		LocalDate fHastaConverted;
		try {
			fDesdeConverted = convertStringToLocalDate(fDesdeString);
			fHastaConverted = convertStringToLocalDate(fHastaString);
		} catch (DateTimeParseException e) {
			throw new OfertaPaqueteException("El formato de la fecha no es el correcto");
		}
		if (validarRangoFechaPaquete(fDesdeConverted, fHastaConverted))
			ofertasPaquete = ofertaDao.buscarOfertasPaquete(destinoId, cantPersonas, fDesdeConverted, fHastaConverted);
		else
			throw new OfertaPaqueteException("La fecha de inicio es mayor que la final o la fecha actual no se encuentra dentro de dicho rangos");
		
		if (ofertasPaquete.isEmpty())
			throw new OfertaPaqueteException("No se encontraron paquetes para el destino id: " + destinoId + " desde el "
					+ fDesdeConverted + " Hasta el " + fHastaConverted+ " cant personas: "+cantPersonas);
		else
			return mapperService.obtenerListaOfertaPaqueteDTO(ofertasPaquete);
	}
	public List<OfertaDTO> buscarOfertaHotelera(int destinoId, String fDesde, String fHasta,String tipoHabitacion) throws OfertaHoteleraException, ConversionFechaException {
		List<Oferta> ofertasHoteleras = null;
		LocalDate fDesdeConverted;
		LocalDate fHastaConverted;
		try {
			fDesdeConverted = convertStringToLocalDate(fDesde);
			fHastaConverted = convertStringToLocalDate(fHasta);
		} catch (DateTimeParseException e) {
			throw new OfertaHoteleraException("El formato de la fecha no es el correcto");
		}
		if (validarRangoFechaHotelera(fDesdeConverted, fHastaConverted))
			ofertasHoteleras = ofertaDao.buscarOfertasHotelera(destinoId, tipoHabitacion, fDesdeConverted, fHastaConverted);
		else
			throw new OfertaHoteleraException("La fecha de inicio es mayor que la final o la fecha actual no se encuentra dentro de dicho rangos");
		
		if (ofertasHoteleras.isEmpty())
			throw new OfertaHoteleraException("No se encontraron hoteles para el destino id: " + destinoId + " desde el "
					+ fDesde + " Hasta el " + fHasta+" tipo habitacion: "+tipoHabitacion);
		else
			return mapperService.obtenerListaOfertaHoteleraDTO(ofertasHoteleras);
	}

	public Boolean validarRangoFechaPaquete(LocalDate localDateDesde, LocalDate localDateHasta) {
		 /* Estamos validando que fDesde no sea mayor que fHasta y que las mismas sean mayor a la fecha actual
		 */
		LocalDate localDateFechaActual = LocalDate.now();
		if (localDateDesde.compareTo(localDateFechaActual) >= 0 && localDateHasta.compareTo(localDateFechaActual) >= 0
				&& localDateHasta.compareTo(localDateDesde) >= 0)
			return true;
		else
			return false;
	}
	public Boolean validarRangoFechaHotelera(LocalDate localDateDesde, LocalDate localDateHasta) {
		 /* Estamos validando que fDesde no sea mayor que fHasta y que la fecha actual se
		 * encuentre dentro de dichos rangos
		 */
		LocalDate localDateFechaActual = LocalDate.now();
		if (localDateHasta.compareTo(localDateFechaActual) >= 0
				&& localDateHasta.compareTo(localDateDesde) >= 0)
			return true;
		else
			return false;
	}
	public LocalDate convertStringToLocalDate(String stringFecha) throws ConversionFechaException {
		//Estamos validando que la fecha tenga el formato correcto
		//ejemplo 2018-06-20T12:30-02:00
		try {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
		//ISO_OFFSET_DATE_TIME	Date Time with Offset	2011-12-03T10:15:30+01:00'
		LocalDate localDate = LocalDate.parse(stringFecha, formatter);
		return localDate;
		}catch(DateTimeParseException e) {
			throw new ConversionFechaException("El formato de la fechas ingresadas no es valido. Ejemplo fecha valida: 2018-04-05T12:30-02:00");
			}
		}
}
