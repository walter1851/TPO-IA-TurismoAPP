package com.turismo.rest.testing.producer;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.turismo.qconsumer.mensajes.OfertaHoteleraMensaje;
import com.turismo.qconsumer.mensajes.OfertaPaqueteMensaje;
import com.turismo.rest.mensajes.WebResponse;
import com.turismo.testing.qproducer.ProducerOfertaHoteleraQ;
import com.turismo.testing.qproducer.ProducerOfertaPaqueteQ;

@Path("/backoffice")
@Stateless
public class BackOfficeProducer {
	@EJB
	private ProducerOfertaHoteleraQ testingQueueHoteleria;
	@EJB
	private ProducerOfertaPaqueteQ testingQueuePaquete;

	@POST
	@Path("ofertahotelera/grabar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarOfertaHotelera(OfertaHoteleraMensaje ofertaHoteleraMensaje) {
		try {
			testingQueueHoteleria.sendMessage(ofertaHoteleraMensaje);
			return Response.ok(new WebResponse(ofertaHoteleraMensaje, "Se grabo en la cola la oferta hotelera"))
					.build();
		} catch (Exception e) {
			return Response.ok(new WebResponse(e.getMessage(), "EXCEPTION")).build();
		}
	}
	@POST
	@Path("ofertapaquete/grabar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarOfertaPaquete(OfertaPaqueteMensaje ofertaPaqueteMensaje) {
		try {
			testingQueuePaquete.sendMessage(ofertaPaqueteMensaje);
			return Response.ok(new WebResponse(ofertaPaqueteMensaje, "Se grabo en la cola la oferta paquete")).build();
		} catch (Exception e) {
			return Response.ok(new WebResponse(e.getMessage(), "EXCEPTION")).build();
		}
	}
}
