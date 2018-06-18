package com.turismo.testing.borrar;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;

import com.turismo.integracion.qconsumer.JsonConverter;
import com.turismo.integraciones.qconsumer.mensajes.CiudadMensaje;
import com.turismo.integraciones.qconsumer.mensajes.EstablecimientoMensaje;
import com.turismo.integraciones.qconsumer.mensajes.HotelMensaje;
import com.turismo.integraciones.qconsumer.mensajes.MapaMensaje;
import com.turismo.integraciones.qconsumer.mensajes.OfertaHoteleraMensaje;
import com.turismo.integraciones.qconsumer.mensajes.OfertaPaqueteMensaje;

@Stateless
public class ProducerOfertaHoteleraQ {
	
	@Resource(lookup = "java:/jms/queue/OfertaHoteleraQueue")
	private Queue testQueue;

	@Inject
	@JMSConnectionFactory("java:/ConnectionFactory")
	JMSContext context;

	public void sendMessage(String idOfertaHotelera, String nombreOfertaHotelera,float precio,int cupo
			, String mediosDePago,String tipoHabitacion,String idEstablecimiento,String uidBackOffice,String nombreEstablecimiento,String direccionEstablecimiento,
			String idCiudad,String nombreCiudad,String idHotel,String nombreHotel,String urlFotoHotel,
			String descripcionEstablecimiento,String mapaLatitud,String mapaLongitud,String urlFotoEstablecimiento,int cantEstrellas,
			String fechaDesde,String fechaHasta,String politicaCancelacion,String servicios) {
			OfertaHoteleraMensaje ofertaHoteleraMensaje=new OfertaHoteleraMensaje();
			
			EstablecimientoMensaje establecimientoMensaje=new EstablecimientoMensaje();
			establecimientoMensaje.setId(idEstablecimiento);
			establecimientoMensaje.setNombre(nombreEstablecimiento);
			establecimientoMensaje.setEstrellas(cantEstrellas);
			establecimientoMensaje.setDescripcion(descripcionEstablecimiento);
			establecimientoMensaje.setDireccion(direccionEstablecimiento);
			establecimientoMensaje.setFotoestablecimiento(urlFotoEstablecimiento);
			establecimientoMensaje.setUid(uidBackOffice);
			CiudadMensaje ciudadMensaje=new CiudadMensaje();
			ciudadMensaje.setId(idCiudad);
			ciudadMensaje.setNombre(nombreCiudad);
			establecimientoMensaje.setCiudad(ciudadMensaje);
			
			ofertaHoteleraMensaje.setNombre(nombreOfertaHotelera);
			ofertaHoteleraMensaje.setIdOfertaHotelera(idOfertaHotelera);
			ofertaHoteleraMensaje.setEstablecimiento(establecimientoMensaje);
			ofertaHoteleraMensaje.setCupo(cupo);
			ofertaHoteleraMensaje.setFechaDesde(fechaDesde);
			ofertaHoteleraMensaje.setFechaHasta(fechaHasta);
			HotelMensaje hotelMensaje=new HotelMensaje();
			hotelMensaje.setId(Integer.parseInt(idHotel));
			hotelMensaje.setNombre(nombreHotel);
			hotelMensaje.setFotoHotel(urlFotoHotel);
	
			MapaMensaje mapaMensaje=new MapaMensaje();
			mapaMensaje.setLat(mapaLatitud);
			mapaMensaje.setLon(mapaLongitud);
			
			ofertaHoteleraMensaje.setMediosDePago(mediosDePago);
			ofertaHoteleraMensaje.setPoliticas(politicaCancelacion);
			ofertaHoteleraMensaje.setPrecio(precio);
			ofertaHoteleraMensaje.setServicios(servicios);
			ofertaHoteleraMensaje.setTipoHabitacion(tipoHabitacion);
			
		try {
			String jsonMensaje = JsonConverter.convertToJson(ofertaHoteleraMensaje);
			context.createProducer().send(testQueue, jsonMensaje);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
