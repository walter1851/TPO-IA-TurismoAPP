package com.turismo.testing.qproducer;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import com.turismo.qconsumer.JsonConverter;
import com.turismo.qconsumer.mensajes.OfertaPaqueteMensaje;

@Stateless
public class ProducerOfertaPaqueteQ {
	@Resource(lookup = "java:/jms/queue/OfertaPaqueteQueue")
	private Queue testQueue;

	@Inject
	@JMSConnectionFactory("java:/ConnectionFactory")
	JMSContext context;
	public void sendMessage(OfertaPaqueteMensaje ofertaPaqueteMensaje) {
		try {
			String jsonMensaje = JsonConverter.convertToJson(ofertaPaqueteMensaje);
			context.createProducer().send(testQueue, jsonMensaje);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
