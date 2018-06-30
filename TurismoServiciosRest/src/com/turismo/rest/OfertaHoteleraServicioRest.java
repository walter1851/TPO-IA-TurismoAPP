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
import com.turismo.rest.mensajes.*;

@Path("/ofertahotelera")
@Stateless
public class OfertaHoteleraServicioRest {
	@EJB
	private ControllerService facade;
	@EJB
	private BackOfficeLogging loggingBackOffice;

	@GET
	@Path("buscar/{codigoDestino}/{tipoHabitacion}/{cantPersonas}/{fDesde}/{fHasta}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarOfertaHotelera(@PathParam("codigoDestino") int codigoDestino,
			@PathParam("tipoHabitacion") String tipoHabitacion, @PathParam("cantPersonas") int cantTotalPersonas,
			@PathParam("fDesde") String fDesde, @PathParam("fHasta") String fHasta) {
		try {
			List<OfertaDTO> ofertas = facade.buscarOfertaHotelera(codigoDestino, fDesde, fHasta, tipoHabitacion,
					cantTotalPersonas);
			this.loggingBackOffice.info(LoggingAccion.BUSQUEDA_OFERTA_HOTELERA);
			return Response.ok(new WebResponse(ofertas, "SE ENCONTRARON: " + ofertas.size() + " OFERTAS HOTELERAS"))
					.build();
		} catch (Exception e) {
			String nombreException=e.getClass().getName();
			System.out.println(nombreException + " -> " + e.getMessage());
			WebResponse webResponse = new WebResponse(e.getMessage(), nombreException);
			//return Response.status(404).entity(webResponse).build();
			return Response.ok(webResponse).build();
		}
	}

	@GET
	@Path("buscarotros/{codigoDestino}/{CantTotalPersonas}/{tipoHabitacionExcluir}/{id_Hotel}/{fDesde}/{fHasta}")
	@Produces(MediaType.APPLICATION_JSON)
	// Otras habitaciones disponibles del mismo hotel
	public Response buscarOtrasOfertasMismoHotel(@PathParam("codigoDestino") int codigoDestino,
			@PathParam("CantTotalPersonas") int CantTotalPersonas,
			@PathParam("tipoHabitacionExcluir") String tipoHabitacionExcluir, @PathParam("id_Hotel") int id_hotel,
			@PathParam("fDesde") String fDesde, @PathParam("fHasta") String fHasta) {
		try {
			List<OfertaDTO> ofertas = facade.buscarOtrasOfertasMismoHotel(codigoDestino, tipoHabitacionExcluir,
					id_hotel, fDesde, fHasta, CantTotalPersonas);
			// this.loggingBackOffice.info(LoggingAccion.BUSQUEDA_OFERTA_HOTELERA);
			return Response.ok(new WebResponse(ofertas, "SE ENCONTRARON " + ofertas.size()
					+ " OFERTAS HOTELERAS PARA MISMO HOTEL y DISTINTO TIPO DE HABITACION")).build();
		} catch (Exception e) {
			// Response.status(404).entity(e.getMessage()).type("Application/json").build();
			String nombreException=e.getClass().getName();
			System.out.println(nombreException + " -> " + e.getMessage());
			WebResponse webResponse = new WebResponse(e.getMessage(), nombreException);
			//return Response.status(404).entity(webResponse).build();
			return Response.ok(webResponse).build();
		}
	}

	@GET
	@Path("calculartotal/{ofertaId}/{tipoHabitacion}/{cantTotalPersonas}/{fDesde}/{fHasta}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response calcularTotalEstadia(@PathParam("ofertaId") int ofertaId,
			@PathParam("tipoHabitacion") String tipoHabitacion, @PathParam("cantTotalPersonas") int cantTotalPersonas,
			@PathParam("fDesde") String fDesde, @PathParam("fHasta") String fHasta) {
		try {
			float total = facade.calcularPrecioTotalHotel(ofertaId, tipoHabitacion, cantTotalPersonas, fDesde, fHasta);
			return Response.ok(new WebResponse(Float.toString(total), "Se calculo el total")).build();
		} catch (Exception e) {
			// getAcceptType()).build();
			//return Response.status(404).entity(webResponse).build();
			String nombreException=e.getClass().getName();
			System.out.println(nombreException + " -> " + e.getMessage());
			WebResponse webResponse = new WebResponse(e.getMessage(), nombreException);
			return Response.ok(webResponse).build();
		}
	}
}
