package com.reservas.serviciosrest;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.reservas.controller.Controller;
import com.reservas.dto.OfertaDTO;
import com.reservas.rerviciosrest.response.WebResponse;

@Path("/ofertapaquete")
@Stateless
public class OfertaPaqueteServiceRest {
	@EJB
	private Controller facade;
	@POST
	@Path("/buscar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("destino") String destino, int cantPersonas,String fDesde,String fHasta) {
		try {
			List<OfertaDTO> ofertas = facade.buscarOfertaPaquete(destino, cantPersonas, fDesde, fHasta);
			return Response.ok(new WebResponse(ofertas)).build();
		} catch (Exception e) {
			//logearerror(e.getMessage());
			return Response.ok(new WebResponse(e.getMessage())).build();
		}
	}
}
