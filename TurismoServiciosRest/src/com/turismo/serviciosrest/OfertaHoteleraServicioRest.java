package com.turismo.serviciosrest;

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

import com.turismo.controller.ControllerService;
import com.turismo.dto.OfertaDTO;
import com.turismo.rerviciosrest.response.*;

@Path("/ofertahotelera")
@Stateless
public class OfertaHoteleraServicioRest {
	@EJB
	private ControllerService facade;

	@GET
	@Path("buscar/{destino}/{cantPersonas}/{fDesde}/{fHasta}/{tipoHabitacion}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarOfertaHotelera(@PathParam("destino") String destino, @PathParam("cantPersonas") int cantPersonas,
			@PathParam("fDesde") String fDesde, @PathParam("fHasta") String fHasta,
			@PathParam("tipoHabitacion") String tipoHabitacion) {
		try {
			List<OfertaDTO> ofertas = facade.buscarOfertaHotelera(destino, cantPersonas, fDesde, fHasta,
					tipoHabitacion);
			return Response.ok(new WebResponse(ofertas)).build();
		} catch (Exception e) {
			// logearerror(e.getMessage());
			return Response.ok(new WebResponse(false, e.getMessage())).build();
		}
	}
}