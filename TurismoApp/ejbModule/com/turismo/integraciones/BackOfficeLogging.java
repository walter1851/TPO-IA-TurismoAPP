package com.turismo.integraciones;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

import org.jboss.logging.Logger;
@Stateless
public class BackOfficeLogging {
	private static Logger LOGGER = Logger.getLogger(BackOfficeLogging.class);

	// @Resource(lookup = "java:/jms/topic/portalWebTopicTest")
	private Queue queue;

	@Inject
	JMSContext context;
	public BackOfficeLogging() {
	}
	public void error(String desc) {
		try {
			if (this.queue == null) return;
			LoggingMensaje lm = new LoggingMensaje();
			lm.setAccion(LoggingAccion.ERROR.getId());
			lm.setDescripcion(desc);
			ObjectMessage message = this.context.createObjectMessage(lm);
			this.context.createProducer().send(this.queue, message);
		} catch (Exception e) {
			BackOfficeLogging.LOGGER.error(e.getMessage(), e);
		}
	}

	public void info(LoggingAccion action) {
		try {
			if (this.queue == null) return;
			LoggingMensaje loggingMensaje = new LoggingMensaje();
			loggingMensaje.setAccion(action.getId());
			ObjectMessage message = this.context.createObjectMessage(loggingMensaje);
			this.context.createProducer().send(this.queue, message);
		} catch (Exception e) {
			BackOfficeLogging.LOGGER.error(e.getMessage(), e);
		}
	}
}
