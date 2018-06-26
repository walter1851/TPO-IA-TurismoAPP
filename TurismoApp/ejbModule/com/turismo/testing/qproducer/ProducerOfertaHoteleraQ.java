package com.turismo.testing.qproducer;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import com.turismo.qconsumer.JsonConverter;
import com.turismo.qconsumer.mensajes.CiudadMensaje;
import com.turismo.qconsumer.mensajes.EstablecimientoMensaje;
import com.turismo.qconsumer.mensajes.HotelMensaje;
import com.turismo.qconsumer.mensajes.MapaMensaje;
import com.turismo.qconsumer.mensajes.OfertaHoteleraMensaje;

@Stateless
public class ProducerOfertaHoteleraQ {
	
	@Resource(lookup = "java:/jms/queue/OfertaHoteleraQueue")
	private Queue testQueue;

	@Inject
	@JMSConnectionFactory("java:/ConnectionFactory")
	JMSContext context;

	public void sendMessage(OfertaHoteleraMensaje ofertaHoteleraMensaje) {
		try {
			String jsonMensaje = JsonConverter.convertToJson(ofertaHoteleraMensaje);
			context.createProducer().send(testQueue, jsonMensaje);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
