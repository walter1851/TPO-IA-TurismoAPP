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
import com.turismo.dto.MedioPagoDTO;
import com.turismo.rerviciosrest.response.WebResponse;

@Path("/reserva")
@Stateless
public class ReservaServicioRest{
	@EJB
	private ControllerService facade;
	@POST
	@Path("/buscar")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscar(@PathParam("ofertaid") int ofertaid,@PathParam("fDesde") String fDesde,@PathParam("fHasta") String fHasta,@PathParam("tipoHabitacion") String tipoHabitacion,@PathParam("cantPersonas") int cantPersonas,@PathParam("nombre") String nombre,@PathParam("apellido") String apellido,@PathParam("dni") String dni,@PathParam("medioPago") String medioPago) {
		try {
			facade.reservarHotel(ofertaid, fDesde, fHasta, tipoHabitacion, cantPersonas, nombre, apellido, dni, medioPago);
			return Response.ok(new WebResponse(true,"ok")).build();
		} catch (Exception e) {
			//logearerror(e.getMessage());
			return Response.ok(new WebResponse(e.getMessage())).build();
		}
	}
}
