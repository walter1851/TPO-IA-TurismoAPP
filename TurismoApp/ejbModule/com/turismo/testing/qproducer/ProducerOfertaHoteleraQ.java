package com.turismo.testing.qproducer;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;

import com.turismo.qconsumer.JsonConverter;
import com.turismo.qconsumer.mensajes.CiudadMensaje;
import com.turismo.qconsumer.mensajes.EstablecimientoMensaje;
import com.turismo.qconsumer.mensajes.HotelMensaje;
import com.turismo.qconsumer.mensajes.MapaMensaje;
import com.turismo.qconsumer.mensajes.OfertaHoteleraMensaje;
import com.turismo.qconsumer.mensajes.OfertaPaqueteMensaje;

@Stateless
public class ProducerOfertaHoteleraQ {
	
	@Resource(lookup = "java:/jms/queue/OfertaHoteleraQueue")
	private Queue testQueue;

	@Inject
	@JMSConnectionFactory("java:/ConnectionFactory")
	JMSContext context;

	public void sendMessage(int idOfertaHotelera, String nombreOfertaHotelera,float precio,int cupo
			, String mediosDePago,String tipoHabitacion,int idEstablecimiento,String uidBackOffice,String nombreEstablecimiento,String direccionEstablecimiento,
			int codigo_ciudad,int idHotel,String nombreHotel,String urlFotoHotel,
			String descripcionEstablecimiento,String mapaLatitud,String mapaLongitud,String urlFotoEstablecimiento,int cantEstrellas,
			String fechaDesde,String fechaHasta,String politicaCancelacion,String servicios) {
			OfertaHoteleraMensaje ofertaHoteleraMensaje=new OfertaHoteleraMensaje();
			
			MapaMensaje mapaMensaje=new MapaMensaje();
			mapaMensaje.setLat(mapaLatitud);
			mapaMensaje.setLon(mapaLongitud);
			
			HotelMensaje hotelMensaje=new HotelMensaje();
			hotelMensaje.setId(idHotel);
			hotelMensaje.setNombre(nombreHotel);
			hotelMensaje.setFotoHotel(urlFotoHotel);
			
			EstablecimientoMensaje establecimientoMensaje=new EstablecimientoMensaje();
			establecimientoMensaje.setId(idEstablecimiento);
			establecimientoMensaje.setNombre(nombreEstablecimiento);
			establecimientoMensaje.setEstrellas(cantEstrellas);
			establecimientoMensaje.setDescripcion(descripcionEstablecimiento);
			establecimientoMensaje.setDireccion(direccionEstablecimiento);
			establecimientoMensaje.setFotoestablecimiento(urlFotoEstablecimiento);
			establecimientoMensaje.setUid(uidBackOffice);
			establecimientoMensaje.setHotel(hotelMensaje);
			establecimientoMensaje.setMapa(mapaMensaje);
			
			CiudadMensaje ciudadMensaje=new CiudadMensaje();
			ciudadMensaje.setCodigo_ciudad(codigo_ciudad);
			//ciudadMensaje.setNombre(nombreCiudad);
			establecimientoMensaje.setCiudad(ciudadMensaje);
			
			ofertaHoteleraMensaje.setNombre(nombreOfertaHotelera);
			ofertaHoteleraMensaje.setIdOfertaHotelera(idOfertaHotelera);
			ofertaHoteleraMensaje.setEstablecimiento(establecimientoMensaje);
			ofertaHoteleraMensaje.setCupo(cupo);
			ofertaHoteleraMensaje.setFechaDesde(fechaDesde);
			ofertaHoteleraMensaje.setFechaHasta(fechaHasta);
			
	
			
			
			ofertaHoteleraMensaje.setMediosDePago(mediosDePago);
			ofertaHoteleraMensaje.setPoliticas(politicaCancelacion);
			ofertaHoteleraMensaje.setPrecio(precio);
			ofertaHoteleraMensaje.setServicios(servicios);
			ofertaHoteleraMensaje.setTipoHabitacion(tipoHabitacion);
			
		try {
			String jsonMensaje = JsonConverter.convertToJson(ofertaHoteleraMensaje);
			context.createProducer().send(testQueue, jsonMensaje);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
