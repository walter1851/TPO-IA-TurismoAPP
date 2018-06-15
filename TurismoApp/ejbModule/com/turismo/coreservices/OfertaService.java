package com.turismo.coreservices;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.turismo.dao.DestinoDAO;
import com.turismo.dao.HotelDAO;
import com.turismo.dao.OfertaBloqueDAO;
import com.turismo.dao.OfertaDAO;
import com.turismo.entities.Agencia;
import com.turismo.entities.Destino;
import com.turismo.entities.Establecimiento;
import com.turismo.entities.Hotel;
import com.turismo.entities.MedioPago;
import com.turismo.entities.Oferta;
import com.turismo.entities.OfertaTipo;
import com.turismo.integraciones.qconsumer.OfertaHoteleraMensaje;
import com.turismo.integraciones.qconsumer.OfertaPaqueteMensaje;

@Stateless
@LocalBean
public class OfertaService {
	@EJB
	private OfertaDAO ofertaDAO;
	@EJB
	private DestinoDAO destinoDAO;
	@EJB
	private OfertaBloqueDAO ofertaBloqueDAO;
	@EJB
	private EstablecimientoService establecimientoService;
	@EJB
	private HotelDAO hotelDAO;
	@EJB
	private AgenciaService agenciaService;

	public void guardarOfertaPaquete(OfertaPaqueteMensaje ofertaPaqueteMensaje) {
		//Despues lo veo bien, primero tengo que analizar como mapea JACKSON
		String nombreOferta=null; 
		int cupo=1; 
		LocalDateTime fecha_desde=null; 
		LocalDateTime fecha_hasta=null;
		float precio=1; 
		String politicas=null; 
		String servicios=null; 
		String nombreDestino=null; 
		String foto_paquete=null;
		MedioPago medioPago=null; 
		int cant_personas=1; 
		String codigo_agencia=null; 
		String direccionAgencia=null;
		String nombreAgencia=null; 
		OfertaTipo ofertaTipo=null;
		Destino destino = destinoDAO.buscarDestinoPorNombre(nombreDestino);
		Agencia agencia = agenciaService.guardarAgencia(codigo_agencia, direccionAgencia, nombreAgencia);
		Oferta nuevaOferta = ofertaDAO.nuevaOfertaPaquete(nombreOferta, cupo, fecha_desde, fecha_hasta, precio,
				politicas, servicios, destino, foto_paquete, medioPago, cant_personas, agencia, ofertaTipo);
		LocalDateTime fechaPivote = fecha_desde;
		// igual a cero significa q son iguales
		// Lo que estoy haciendo es generar los bloques de acuerdo a la cantidad de dias
		while (fechaPivote.compareTo(fecha_hasta) == 0) {
			ofertaBloqueDAO.nuevoBloque(nuevaOferta, fechaPivote, cupo);
			fechaPivote.plusDays(1);
		}
	}

	public void guardarOfertaHotelera(OfertaHoteleraMensaje ofertaHoteleraMensaje) {
	//Antes empezar a guardar los objetos, necesito ver como me mapeo jackson	
	String nombreOferta=null; 
		String nombreDestino=null; 
		int cupo=1; 
		LocalDateTime fecha_desde=null;
		LocalDateTime fecha_hasta=null; 
		float precio=0; 
		String tipo_habitacion=null; 
		String politicas=null;
		String servicios=null;
		String foto_paquete=null;
		MedioPago medioPago=null; 
		int cant_personas=1; 
		String nombreEstablecimiento=null;
		String direccionEstablecimiento=null; 
		String ciudadEstablecimiento=null; 
		String estadoEstablecimiento=null;
		String descripcionEstablecimiento=null; 
		String estrellasEstablecimiento=null; 
		String mapaEstablecimiento=null;
		String codigo_establecimiento=null; 
		String nombreHotel=null; 
		String codigo_hotel=null; 
		OfertaTipo ofertaTipo=null;
		Destino destino = destinoDAO.buscarDestinoPorNombre(nombreDestino);
		Hotel hotel = hotelDAO.nuevoHotel(nombreHotel, codigo_hotel);
		Establecimiento establecimiento = establecimientoService.guardarEstablecimiento(nombreEstablecimiento,
				direccionEstablecimiento, ciudadEstablecimiento, estadoEstablecimiento, descripcionEstablecimiento,
				estrellasEstablecimiento, mapaEstablecimiento, codigo_establecimiento, hotel);
		Oferta nuevaOferta = ofertaDAO.nuevaOfertaHotelera(nombreOferta, cupo, fecha_desde, fecha_hasta, precio,
				tipo_habitacion, politicas, servicios, destino, foto_paquete, medioPago, cant_personas, establecimiento,
				ofertaTipo);
		LocalDateTime fechaPivote = fecha_desde;
		// igual a cero significa q son iguales
		// Lo que estoy haciendo es generar los bloques de acuerdo a la cantidad de dias
		while (fechaPivote.compareTo(fecha_hasta) == 0) {
			ofertaBloqueDAO.nuevoBloque(nuevaOferta, fechaPivote, cupo);
			fechaPivote.plusDays(1);
		}
	}
}
