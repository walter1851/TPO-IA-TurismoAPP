package com.turismo.testing.borrar;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.turismo.controller.ControllerService;
import com.turismo.dto.OfertaDTO;
import com.turismo.exceptions.OfertaPaqueteException;
import com.turismo.serviciorest.response.WebResponse;

@Path("/backoffice")
@Stateless
public class ProducerOfertaPaqueteRest {
	@EJB
	private ProducerOfertaPaqueteQ testingQueue;
	@GET
	@Path("ofertapaquete/grabar/{idPaquete}/{nombrePaquete}/{idCiudadDestino}//{cupo}/{cantPersonas}/{idAgencia}/{nombreAgencia}/{direccionAgencia}/{estadoAgencia}/{foto}/{fechaDesde}/{fechaHasta}/{estado}/{precio}/{descripcion}/{politicaCancelacion}/{servicios}/{mediosDePago}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarOfertaPaquete(@PathParam("idPaquete") int idPaquete,
			@PathParam("nombrePaquete") String nombrePaquete, @PathParam("idCiudadDestino") int idCiudadDestino,
			@PathParam("cupo") int cupo,
			@PathParam("cantPersonas") int cantPersonas, @PathParam("idAgencia") int idAgencia,
			@PathParam("nombreAgencia") String nombreAgencia,
			@PathParam("direccionAgencia") String direccionAgencia, @PathParam("estadoAgencia") String estadoAgencia,
			@PathParam("foto") String foto,
			@PathParam("fechaDesde") String fechaDesde, @PathParam("fechaHasta") String fechaHasta,
			@PathParam("estado") String estado, @PathParam("precio") float precio,
			@PathParam("descripcion") String descripcion, @PathParam("politicaCancelacion") String politicaCancelacion,
			@PathParam("servicios") String servicios,
			@PathParam("mediosDePago") String mediosDePago) {
		try {
			testingQueue.sendMessage(idPaquete, nombrePaquete, idCiudadDestino, cupo, cantPersonas,
					idAgencia, nombreAgencia, direccionAgencia, estadoAgencia, foto,
					fechaDesde, fechaHasta, estado, precio, descripcion, politicaCancelacion,
					servicios, mediosDePago);
			return Response.ok(new WebResponse(true, "Se grabo en la cola la oferta paquete")).build();
		} catch (Exception e) {
			// logearerror(e.getMessage());
			return Response.ok(new WebResponse(false, e.getMessage())).build();
		}
	}
}
