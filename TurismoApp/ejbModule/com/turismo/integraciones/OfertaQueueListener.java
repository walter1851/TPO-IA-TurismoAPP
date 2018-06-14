package com.turismo.integraciones;

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
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/queue/TurismoQueue"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jms/queue/TurismoQueue")
/*En este caso supongo que hay una cola, y desde ahi consumo oferta paquete y oferta hotelera.
Pero dependiendo de como implemente las colas el otro grupo, tendremos que crear a lo sumo
dos MDBs (message driven bean), uno para oferta hotelera y otro para paquete*/
public class OfertaQueueListener implements MessageListener {
@EJB
private OfertaService ofertaService;
@EJB
private AgenciaService agenciaService;
    public OfertaQueueListener() {
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
			//Desp completo
  
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
    }

}
