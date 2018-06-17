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

import com.turismo.integraciones.qconsumer.JsonConverter;
import com.turismo.integraciones.qconsumer.OfertaHoteleraMensaje;
import com.turismo.integraciones.qconsumer.OfertaPaqueteMensaje;

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
			ofertaHoteleraMensaje.setCantEstrellas(cantEstrellas);
			ofertaHoteleraMensaje.setCupo(cupo);
			ofertaHoteleraMensaje.setDescripcionEstablecimiento(descripcionEstablecimiento);
			ofertaHoteleraMensaje.setDireccionEstablecimiento(direccionEstablecimiento);
			ofertaHoteleraMensaje.setFechaDesde(fechaDesde);
			ofertaHoteleraMensaje.setFechaHasta(fechaHasta);
			ofertaHoteleraMensaje.setIdCiudad(idCiudad);
			ofertaHoteleraMensaje.setIdEstablecimiento(idEstablecimiento);
			ofertaHoteleraMensaje.setIdHotel(idHotel);
			ofertaHoteleraMensaje.setIdOfertaHotelera(idOfertaHotelera);
			ofertaHoteleraMensaje.setMapaLatitud(mapaLatitud);
			ofertaHoteleraMensaje.setMapaLongitud(mapaLongitud);
			ofertaHoteleraMensaje.setMediosDePago(mediosDePago);
			ofertaHoteleraMensaje.setNombreCiudad(nombreCiudad);
			ofertaHoteleraMensaje.setNombreEstablecimiento(nombreEstablecimiento);
			ofertaHoteleraMensaje.setNombreHotel(nombreHotel);
			ofertaHoteleraMensaje.setNombreOfertaHotelera(nombreOfertaHotelera);
			ofertaHoteleraMensaje.setPoliticaCancelacion(politicaCancelacion);
			ofertaHoteleraMensaje.setPrecio(precio);
			ofertaHoteleraMensaje.setServicios(servicios);
			ofertaHoteleraMensaje.setTipoHabitacion(tipoHabitacion);
			ofertaHoteleraMensaje.setUidBackOffice(uidBackOffice);
			ofertaHoteleraMensaje.setUrlFotoEstablecimiento(urlFotoEstablecimiento);
			ofertaHoteleraMensaje.setUrlFotoHotel(urlFotoHotel);
			
		try {
			String jsonMensaje = JsonConverter.convertToJson(ofertaHoteleraMensaje);
			context.createProducer().send(testQueue, jsonMensaje);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
