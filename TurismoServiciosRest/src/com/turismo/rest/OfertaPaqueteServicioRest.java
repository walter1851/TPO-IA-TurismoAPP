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
import com.turismo.rest.mensajes.WebResponse;

@Path("/ofertapaquete")
@Stateless
public class OfertaPaqueteServicioRest {
	@EJB
	private ControllerService facade;
	@EJB
	private BackOfficeLogging loggingBackOffice;

	@GET
	@Path("buscar/{codigoDestino}/{cantPersonas}/{fDesde}/{fHasta}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarOfertaPaquete(@PathParam("codigoDestino") int codigoDestino,
			@PathParam("cantPersonas") int cantPersonas, @PathParam("fDesde") String fDesde,
			@PathParam("fHasta") String fHasta) {
		try {
			List<OfertaDTO> ofertas = facade.buscarOfertaPaquete(codigoDestino, cantPersonas, fDesde, fHasta);
			this.loggingBackOffice.info(LoggingAccion.BUSQUEDA_OFERTA_PAQUETE);
			return Response.ok(new WebResponse(ofertas, "SE ENCONTRARON: " + ofertas.size() + " PAQUETES")).build();
		} catch (Exception e) {
			return Response.ok(new WebResponse(e.getMessage(), "EXCEPTION")).build();
		}
	}
	@GET
	@Path("buscarotros/{idPaqueteExcluir}/{codigoDestino}/{cantPersonas}/{fDesde}/{fHasta}")
	@Produces(MediaType.APPLICATION_JSON)
	//Otras paquetes del mismo destino
	public Response buscarOtrosPaquetes(@PathParam("idPaqueteExcluir") int idPaqueteExcluir,
			@PathParam("codigoDestino") int codigoDestino, @PathParam("cantPersonas") int cantPersonas,
			@PathParam("fDesde") String fDesde, @PathParam("fHasta") String fHasta) {
		try {
			List<OfertaDTO> ofertas = facade.buscarOtrosPaquetesMismoDestino(idPaqueteExcluir, codigoDestino,
					cantPersonas, fDesde, fHasta);
			//this.loggingBackOffice.info(LoggingAccion.BUSQUEDA_OFERTA_PAQUETE);
			return Response.ok(
					new WebResponse(ofertas, "ENCONTRADOS: " + ofertas.size() + " - DISTINTOS PAQUETES MISMO DESTINO"))
					.build();
		} catch (Exception e) {
			return Response.ok(new WebResponse(e.getMessage(), "EXCEPTION")).build();
		}
	}

	@GET
	@Path("calculartotal/{ofertaId}/{cantidadPersonas}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response calculartotalPaquete(@PathParam("ofertaId") int ofertaId,
			@PathParam("cantidadPersonas") int cantidadPersonas) {
		try {
			float total = facade.calcularPrecioTotalPaquete(ofertaId, cantidadPersonas);
			return Response.ok(new WebResponse(Float.toString(total), "Se calculo el total")).build();
		} catch (Exception e) {
			return Response.ok(new WebResponse(e.getMessage(), "EXCEPTION")).build();
		}
	}
}
