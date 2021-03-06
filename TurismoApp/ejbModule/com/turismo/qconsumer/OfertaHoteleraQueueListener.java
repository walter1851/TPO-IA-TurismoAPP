package com.turismo.qconsumer;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.turismo.coreservices.OfertaService;
import com.turismo.qconsumer.mensajes.OfertaHoteleraMensaje;

/**
 * Message-Driven Bean implementation class for: OfertaHoteleraQueueListener
 */
/*
//COLA LOCAL (SOLO TESTING) BORRAR
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/queue/OfertaHoteleraQueue"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jms/queue/OfertaHoteleraQueue")
 */
//COLA REMOTA GRUPO DEL SABADO GRUPO 6
@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty(
				propertyName = "destination", 
				propertyValue = "jms/queue/ofertaHotelera"), 
				@ActivationConfigProperty(
						propertyName = "destinationType", 
							propertyValue = "javax.jms.Queue"),
				@ActivationConfigProperty(propertyName = "user", 
				propertyValue = "integracion"), 
				@ActivationConfigProperty(propertyName = "password", 
				propertyValue = "integracion"),
				@ActivationConfigProperty(propertyName = "connectionParameters", 
				propertyValue = "host=192.168.0.169;port=8080; http-upgrade-enabled=true"),
				@ActivationConfigProperty(propertyName = "connectorClassName",
        		propertyValue = "org.hornetq.core.remoting.impl.netty.NettyConnectorFactory")
		}, 
		mappedName = "jms/queue/ofertaHotelera")
public class OfertaHoteleraQueueListener implements MessageListener {
	@EJB
	private OfertaService ofertaService;
    public OfertaHoteleraQueueListener() {
        // TODO Auto-generated constructor stub
    }

    public void onMessage(Message message) {
    	try {
			String jsonString = ((TextMessage) message).getText();
			System.out.println(jsonString);
			OfertaHoteleraMensaje ofertaHoteleraMensaje = (OfertaHoteleraMensaje) JsonConverter.convertToObject(jsonString,
					OfertaHoteleraMensaje.class);
			ofertaService.guardarOfertaHotelera(ofertaHoteleraMensaje);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
