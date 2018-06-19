package com.turismo.testing.qproducer;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

import com.turismo.qconsumer.JsonConverter;
import com.turismo.qconsumer.mensajes.AgenciaMensaje;
import com.turismo.qconsumer.mensajes.CiudadMensaje;
import com.turismo.qconsumer.mensajes.OfertaPaqueteMensaje;

@Stateless
public class ProducerOfertaPaqueteQ {
	@Resource(lookup = "java:/jms/queue/OfertaPaqueteQueue")
	private Queue testQueue;

	@Inject
	@JMSConnectionFactory("java:/ConnectionFactory")
	JMSContext context;
	public void sendMessage(int idPaquete, String nombrePaquete,int codigo_ciudadDestino,int cupo,
			int cantPersonas, int idAgencia,String nombreAgencia,String direccionAgencia,String estadoAgencia,String foto,
			String fechaDesde,String fechaHasta,String estado,float precio, String descripcion, String politicaCancelacion,String servicios,String mediosDePago) {
			
			AgenciaMensaje agenciaMensaje=new AgenciaMensaje();
			agenciaMensaje.setId(idAgencia);
			agenciaMensaje.setDireccion(direccionAgencia);
			agenciaMensaje.setEstado(estadoAgencia);
			agenciaMensaje.setNombre(nombreAgencia);
			
			CiudadMensaje ciudadDestino= new CiudadMensaje();
			ciudadDestino.setCodigo_ciudad(codigo_ciudadDestino);
			
			OfertaPaqueteMensaje ofertaPaqueteMensaje=new OfertaPaqueteMensaje();
			ofertaPaqueteMensaje.setAgencia(agenciaMensaje);
			ofertaPaqueteMensaje.setCiudadDestino(ciudadDestino);
			ofertaPaqueteMensaje.setId(idPaquete);
			ofertaPaqueteMensaje.setNombre(nombrePaquete);
			ofertaPaqueteMensaje.setCantPersonas(cantPersonas);
			ofertaPaqueteMensaje.setCupo(cupo);
			ofertaPaqueteMensaje.setDescripcion(descripcion);
			ofertaPaqueteMensaje.setEstado(estado);
			ofertaPaqueteMensaje.setFechaDesde(fechaDesde);
			ofertaPaqueteMensaje.setFechaHasta(fechaHasta);
			ofertaPaqueteMensaje.setFoto(foto);
			ofertaPaqueteMensaje.setMediosDePago(mediosDePago);
			ofertaPaqueteMensaje.setPoliticas(politicaCancelacion);
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
