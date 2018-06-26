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
			@PathParam("fDesde") String fDesde,@PathParam("fHasta") String fHasta
			){
		try {
			List<OfertaDTO> ofertas = facade.buscarOfertaHotelera(codigoDestino, fDesde, fHasta, tipoHabitacion,cantTotalPersonas);
			this.loggingBackOffice.info(LoggingAccion.BUSQUEDA_OFERTA_HOTELERA);
			return Response.ok(new WebResponse(ofertas,"SE ENCONTRARON: "+ofertas.size()+" OFERTAS HOTELERAS")).build();
		} catch (Exception e) {
			return Response.ok(new WebResponse(e.getMessage(),"EXCEPTION")).build();
		}
	}
	@GET
	@Path("buscarotros/{codigoDestino}/{tipoHabitacionExcluir}/{id_Hotel}/{fDesde}/{fHasta}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarOtrasOfertasMismoHotel(@PathParam("codigoDestino") int codigoDestino,
			@PathParam("tipoHabitacionExcluir") String tipoHabitacionExcluir, @PathParam("id_Hotel") int id_hotel,
			@PathParam("fDesde") String fDesde,
			@PathParam("fHasta") String fHasta){
		try {
			List<OfertaDTO> ofertas = facade.buscarOtrasOfertasMismoHotel(codigoDestino, tipoHabitacionExcluir, id_hotel, fDesde, fHasta);
			//this.loggingBackOffice.info(LoggingAccion.BUSQUEDA_OFERTA_HOTELERA);
			return Response.ok(new WebResponse(ofertas,"SE ENCONTRARON "+ofertas.size()+" OFERTAS HOTELERAS PARA MISMO HOTEL y DISTINTO TIPO DE HABITACION")).build();
		} catch (Exception e) {
			return Response.ok(new WebResponse(e.getMessage(),"EXCEPTION")).build();
		}
	}
	@GET
	@Path("calculartotal/{ofertaId}/{tipoHabitacion}/{cantTotalPersonas}/{fDesde}/{fHasta}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response calcularTotalEstadia(@PathParam("ofertaId") int ofertaId,
			@PathParam("tipoHabitacion") String tipoHabitacion,
		    @PathParam("cantTotalPersonas") int cantTotalPersonas,
			@PathParam("fDesde") String fDesde,@PathParam("fHasta") String fHasta){
		try {
			float total= facade.calcularPrecioTotalHotel(ofertaId, tipoHabitacion, cantTotalPersonas, fDesde, fHasta);
			return Response.ok(new WebResponse(Float.toString(total),"Se calculo el total")).build();
		} catch (Exception e) {
			return Response.ok(new WebResponse(e.getMessage(),"EXCEPTION")).build();
		}
	}
}

