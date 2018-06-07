package com.turismo.serviciosrest;
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
import com.turismo.rerviciosrest.response.WebResponse;

@Path("/ofertapaquete")
public class OfertaPaqueteServicioRest {
	@EJB
	private ControllerService facade;
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
	@GET
	@Path("/test")
	@Produces({ "text/plain" })
	public String test(){
		try {
			return "ESTO ES UNA PRUEBA exitosa!";
		} catch (Exception e) {
			return "ESTO ES UN error!";
		}
	}
}
