package com.turismo.backoffice.logging;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import com.turismo.exceptions.LoggingException;
import com.turismo.qconsumer.JsonConverter;

@Stateless
public class BackOfficeLogging {
	@Resource(lookup = "java:/jms/queue/BackOfficeLoggingQueue")
	private Queue queue;

	@Inject
	@JMSConnectionFactory("java:/ConnectionFactory") //Comentar si no funciona con cola remota
	JMSContext context;
	
	public BackOfficeLogging() {
	}
	public void info(LoggingAccion accion) throws LoggingException {
		try {
			if (this.queue == null) return;
			LoggingMensaje loggingMensaje = new LoggingMensaje();
			loggingMensaje.setAccion(accion.getId());
			loggingMensaje.setDescripcion(accion.getDescription());
			String jsonMensaje = JsonConverter.convertToJson(loggingMensaje);
			//ObjectMessage message = this.context.createObjectMessage(loggingMensaje); ESTO ES EN CASO MENSAJE COMUN
			//this.context.createProducer().send(this.queue, message); ESTO ES EN CASO MENSAJE COMUN
			context.createProducer().send(queue, jsonMensaje);
		} catch (Exception e) {
			//BackOfficeLogging.LOGGER.error(e.getMessage(), e);
			throw new LoggingException(
					"HUBO UN PROBLEMA PARA REGISTRAR LA ACCION EN EL LOG BACKOFFICE. DETALLE: "+e.getMessage());
		}
	}
}
