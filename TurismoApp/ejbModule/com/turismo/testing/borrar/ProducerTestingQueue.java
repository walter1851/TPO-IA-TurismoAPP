package com.turismo.testing.borrar;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.TextMessage;

import com.turismo.integraciones.qconsumer.JsonConverter;
import com.turismo.integraciones.qconsumer.OfertaPaqueteMensaje;

@Stateless
public class ProducerTestingQueue {
	
	@Resource(lookup = "java:/jms/queue/TurismoQueue")
	private Queue testQueue;

	@Inject
	//@JMSConnectionFactory("java:/ConnectionFactory")
	JMSContext context;

	public void sendMessage(String messageText) {
		/*
		String jsonString = ((TextMessage) messageText).getText();
		OfertaPaqueteMensaje ofertaPaqueteMensaje = (OfertaPaqueteMensaje) JsonConverter.convertToObject(jsonString,
				OfertaPaqueteMensaje.class);
		TextMessage message = context.createTextMessage("Hola, hay alguien ahí?");
		context.createProducer().send(testQueue, message);*/
	}
}
