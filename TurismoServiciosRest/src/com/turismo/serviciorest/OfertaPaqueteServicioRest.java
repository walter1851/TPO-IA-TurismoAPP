package com.turismo.serviciorest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.turismo.controller.ControllerService;
import com.turismo.dto.OfertaDTO;
import com.turismo.exceptions.OfertaPaqueteException;
import com.turismo.serviciorest.response.WebResponse;

@Path("/ofertapaquete")
@Stateless
public class OfertaPaqueteServicioRest {
	@EJB
	private ControllerService facade;
	@GET
	@Path("buscar/{destino}/{cantPersonas}/{fDesde}/{fHasta}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarOfertaPaquete(@PathParam("destino") String destino, @PathParam("cantPersonas") int cantPersonas,
			@PathParam("fDesde") String fDesde, @PathParam("fHasta") String fHasta) {
		try {
			List<OfertaDTO> ofertas = facade.buscarOfertaPaquete(destino, cantPersonas, fDesde, fHasta);
			return Response.ok(new WebResponse(ofertas)).build();
		} catch (OfertaPaqueteException e) {
			// logearerror(e.getMessage());
			return Response.ok(new WebResponse(false, e.getMessage())).build();
		}
	}
}
