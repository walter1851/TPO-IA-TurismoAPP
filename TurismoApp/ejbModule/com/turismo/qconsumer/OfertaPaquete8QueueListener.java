package com.turismo.qconsumer;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import com.turismo.coreservices.OfertaService;
import com.turismo.qconsumer.mensajes.OfertaPaqueteMensaje;

/**
 * Message-Driven Bean implementation class for: OfertaQueueListener
 */

//Grupo 8
@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty(
				propertyName = "destination", 
				propertyValue = "jms/queue/ofertaPaquetes"), 
				@ActivationConfigProperty(
						propertyName = "destinationType", 
							propertyValue = "javax.jms.Queue"),
				@ActivationConfigProperty(propertyName = "user", 
				propertyValue = "integracion"), 
				@ActivationConfigProperty(propertyName = "password", 
				propertyValue = "integracion"),
				@ActivationConfigProperty(propertyName = "connectionParameters", 
				propertyValue = "host=192.168.0.152;port=8080; http-upgrade-enabled=true"),
				@ActivationConfigProperty(propertyName = "connectorClassName",
        		propertyValue = "org.hornetq.core.remoting.impl.netty.NettyConnectorFactory")
		}, 
		mappedName = "jms/queue/ofertaPaquetes")
/*
//COLA LOCAL SOLO TESTING (BORRAR ANTES DE ENTREGAR TP)
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/queue/OfertaPaqueteQueue"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jms/queue/OfertaPaqueteQueue")
		*/
public class OfertaPaquete8QueueListener implements MessageListener {
@EJB
private OfertaService ofertaService;
    public OfertaPaquete8QueueListener() {
        // TODO Auto-generated constructor stub
    }
	
 public void onMessage(Message message) {
    	try {

			String jsonString = ((TextMessage) message).getText();
			OfertaPaqueteMensaje ofertaPaqueteMensaje = (OfertaPaqueteMensaje) JsonConverter.convertToObject(jsonString,
					OfertaPaqueteMensaje.class);
			ofertaService.guardarOfertaPaquete(ofertaPaqueteMensaje);
		} catch (Exception e) {
			e.printStackTrace();
		}

        
    }

}
