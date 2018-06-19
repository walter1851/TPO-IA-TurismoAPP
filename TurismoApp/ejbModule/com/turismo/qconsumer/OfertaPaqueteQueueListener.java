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
/*
 * GRUPO 11 jona.medina@gmail.com
@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty(
				propertyName = "destination", 
				propertyValue = "jms/queue/OP11Queue"), 
				@ActivationConfigProperty(
						propertyName = "destinationType", 
							propertyValue = "javax.jms.Queue"),
				@ActivationConfigProperty(propertyName = "user", 
				propertyValue = "integracion"), 
				@ActivationConfigProperty(propertyName = "password", 
				propertyValue = "integracion"),
				@ActivationConfigProperty(propertyName = "connectionParameters", 
				propertyValue = "host=192.168.130.104;port=8080; http-upgrade-enabled=true"),
				@ActivationConfigProperty(propertyName = "connectorClassName",
        		propertyValue = "org.hornetq.core.remoting.impl.netty.NettyConnectorFactory")
		}, 
		mappedName = "jms/queue/ofertaPaquete")
*/
//COLA LOCAL SOLO TESTING (BORRAR ANTES DE ENTREGAR TP)
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/queue/OfertaPaqueteQueue"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jms/queue/OfertaPaqueteQueue")
public class OfertaPaqueteQueueListener implements MessageListener {
@EJB
private OfertaService ofertaService;
    public OfertaPaqueteQueueListener() {
        // TODO Auto-generated constructor stub
    }
	
 public void onMessage(Message message) {
    	try {

			String jsonString = ((TextMessage) message).getText();
			OfertaPaqueteMensaje ofertaPaqueteMensaje = (OfertaPaqueteMensaje) JsonConverter.convertToObject(jsonString,
					OfertaPaqueteMensaje.class);
			ofertaService.guardarOfertaPaquete(ofertaPaqueteMensaje);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
    }

}
