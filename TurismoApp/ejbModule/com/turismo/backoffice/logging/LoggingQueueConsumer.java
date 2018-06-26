package com.turismo.backoffice.logging;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import com.turismo.qconsumer.JsonConverter;

@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:/jms/queue/BackOfficeLoggingQueue"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "java:/jms/queue/BackOfficeLoggingQueue")
public class LoggingQueueConsumer implements MessageListener {

	public void onMessage(Message message) {
		try {
			String jsonString = ((TextMessage) message).getText();
			LoggingMensaje loggingMensaje = (LoggingMensaje) JsonConverter.convertToObject(jsonString,
					LoggingMensaje.class);
			System.out.println("ID MODULO: "+loggingMensaje.getId_modulo()+" - ID_ACCION: "+loggingMensaje.getId_accion()+" - DESCRIPCION: "+loggingMensaje.getDescripcion()+" - FECHA: "+loggingMensaje.getFecha());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}


}
