package com.turismo.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.turismo.backoffice.logging.BackOfficeLogging;
import com.turismo.backoffice.logging.LoggingAccion;
import com.turismo.controller.ControllerService;
import com.turismo.dto.OfertaDTO;
import com.turismo.rest.response.*;

@Path("/ofertahotelera")
@Stateless
public class OfertaHoteleraServicioRest {
	@EJB
	private ControllerService facade;
	//@EJB
	//private BackOfficeLogging loggingBackOffice;

	@GET
	@Path("buscar/{codigoDestino}/{tipoHabitacion}/{fDesde}/{fHasta}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarOfertaHotelera(@PathParam("codigoDestino") int codigoDestino,
			@PathParam("tipoHabitacion") String tipoHabitacion, @PathParam("fDesde") String fDesde,
			@PathParam("fHasta") String fHasta){
		try {
			List<OfertaDTO> ofertas = facade.buscarOfertaHotelera(codigoDestino, fDesde, fHasta, tipoHabitacion);
			//this.loggingBackOffice.info(LoggingAccion.BUSQUEDA_OFERTA_HOTELERA);
			return Response.ok(new WebResponse(ofertas,"SE ENCONTRARON: "+ofertas.size()+" OFERTAS HOTELERAS")).build();
		} catch (Exception e) {
			//this.loggingBackOffice.error(e.getMessage());
			return Response.ok(new WebResponse(e,"ERROR")).build();
		}
	}
}
