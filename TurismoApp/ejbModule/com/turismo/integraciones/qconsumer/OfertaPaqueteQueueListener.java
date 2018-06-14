package com.turismo.integraciones.qconsumer;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import com.turismo.coreservices.AgenciaService;
import com.turismo.coreservices.OfertaService;
import com.turismo.entities.Agencia;

/**
 * Message-Driven Bean implementation class for: OfertaQueueListener
 */
//cambiar la ruta, por la cola remota
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/queue/TurismoQueue"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jms/queue/TurismoQueue")
public class OfertaPaqueteQueueListener implements MessageListener {
@EJB
private OfertaService ofertaService;
    public OfertaPaqueteQueueListener() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
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
