package com.turismo.testing.borrar;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import com.turismo.integraciones.qconsumer.JsonConverter;
import com.turismo.integraciones.qconsumer.OfertaPaqueteMensaje;

@Stateless
public class ProducerOfertaPaqueteQ {
	@Resource(lookup = "java:/jms/queue/OfertaPaqueteQueue")
	private Queue testQueue;

	@Inject
	@JMSConnectionFactory("java:/ConnectionFactory")
	JMSContext context;
	public void sendMessage(String idPaquete, String nombrePaquete,String idCiudadDestino,String nombreCiudadDestino,int cupo,
			int cantPersonas, String idAgencia,String nombreAgencia,String direccionAgencia,String estadoAgencia,String foto,
			String fechaDesde,String fechaHasta,String estado,float precio, String descripcion, String politicaCancelacion,String servicios,String mediosDePago) {
			OfertaPaqueteMensaje ofertaPaqueteMensaje=new OfertaPaqueteMensaje();
			ofertaPaqueteMensaje.setCantPersonas(cantPersonas);
			ofertaPaqueteMensaje.setCupo(cupo);
			ofertaPaqueteMensaje.setDescripcion(descripcion);
			ofertaPaqueteMensaje.setDireccionAgencia(direccionAgencia);
			ofertaPaqueteMensaje.setEstado(estado);
			ofertaPaqueteMensaje.setEstadoAgencia(estadoAgencia);
			ofertaPaqueteMensaje.setFechaDesde(fechaDesde);
			ofertaPaqueteMensaje.setFechaHasta(fechaHasta);
			ofertaPaqueteMensaje.setFoto(foto);
			ofertaPaqueteMensaje.setIdAgencia(idAgencia);
			ofertaPaqueteMensaje.setIdCiudadDestino(idCiudadDestino);
			ofertaPaqueteMensaje.setIdPaquete(idPaquete);
			ofertaPaqueteMensaje.setMediosDePago(mediosDePago);
			ofertaPaqueteMensaje.setNombreAgencia(nombreAgencia);
			ofertaPaqueteMensaje.setNombreCiudadDestino(nombreCiudadDestino);
			ofertaPaqueteMensaje.setNombrePaquete(nombrePaquete);
			ofertaPaqueteMensaje.setPoliticaCancelacion(politicaCancelacion);
			ofertaPaqueteMensaje.setPrecio(precio);
			ofertaPaqueteMensaje.setServicios(servicios);
		try {
			String jsonMensaje = JsonConverter.convertToJson(ofertaPaqueteMensaje);
			context.createProducer().send(testQueue, jsonMensaje);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
