package com.turismo.integracion.backoffice.logging;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

import org.jboss.logging.Logger;
@Stateless
public class BackOfficeLogging {
	private static Logger LOGGER = Logger.getLogger(BackOfficeLogging.class);

	//Modificar por la dirección de la cola remota
	//@Resource(lookup = "java:/jms/queue/TurismoQueue")
	private Queue remoteQueue;

	@Inject	
	//@JMSConnectionFactory("java:/ConnectionFactory")
	JMSContext context;
	public BackOfficeLogging() {
	}
	public void error(String desc) {
		try {
			if (this.remoteQueue == null) return;
			LoggingMensaje loggingMensaje = new LoggingMensaje();
			loggingMensaje.setAccion(LoggingAccion.ERROR.getId());
			loggingMensaje.setDescripcion(desc);
			ObjectMessage message = this.context.createObjectMessage(loggingMensaje);
			this.context.createProducer().send(this.remoteQueue, message);
		} catch (Exception e) {
			BackOfficeLogging.LOGGER.error(e.getMessage(), e);
		}
	}

	public void info(LoggingAccion action) {
		try {
			if (this.remoteQueue == null) return;
			LoggingMensaje loggingMensaje = new LoggingMensaje();
			loggingMensaje.setAccion(action.getId());
			ObjectMessage message = this.context.createObjectMessage(loggingMensaje);
			this.context.createProducer().send(this.remoteQueue, message);
		} catch (Exception e) {
			BackOfficeLogging.LOGGER.error(e.getMessage(), e);
		}
	}
}
