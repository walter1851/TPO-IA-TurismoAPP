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
import com.turismo.exceptions.OfertaHoteleraException;
import com.turismo.serviciorest.response.*;

@Path("/ofertahotelera")
@Stateless
public class OfertaHoteleraServicioRest {
	@EJB
	private ControllerService facade;

	@GET
	@Path("buscar/{destinoId}/{cantPersonas}/{fDesde}/{fHasta}/{tipoHabitacion}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarOfertaHotelera(@PathParam("destinoId") int destinoId, @PathParam("cantPersonas") int cantPersonas,
			@PathParam("fDesde") String fDesde, @PathParam("fHasta") String fHasta,
			@PathParam("tipoHabitacion") String tipoHabitacion) {
		try {
			List<OfertaDTO> ofertas = facade.buscarOfertaHotelera(destinoId, cantPersonas, fDesde, fHasta,
					tipoHabitacion);
			return Response.ok(new WebResponse(ofertas)).build();
		} catch (OfertaHoteleraException e) {
			// logearerror(e.getMessage());
			return Response.ok(new WebResponse(false, e.getMessage())).build();
		}
	}
}
