package com.turismo.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.rpc.ServiceException;
import javax.xml.ws.WebServiceRef;

import com.backoffice.servicios.SOAPService;
//import com.backoffice.servicios.SOAPService;
//import com.backoffice.servicios.SOAPService;
import com.turismo.controller.ControllerService;
import com.turismo.dto.ReservaDTO;
import com.turismo.rest.response.WebResponse;
//import com.turismo.testing.soapserver.BackOfficeAutorizador;
//import com.turismo.testing.soapserver.BackOfficeAutorizador;
//import com.turismo.testing.soapserver.BackOfficeAutorizadorService;
//import com.turismo.testing.soapserver.BackOfficeAutorizadorService;

@Path("/reserva")
@Stateless
public class ReservaServicioRest {
	@EJB
	private ControllerService facade;
	//@EJB
	//private BackOfficeLogging loggingBackOffice;
	
	//CAMBIAR POR METODO POST
	@GET
	@Path("reservarhotel/{ofertaid}/{fDesde}/{fHasta}/{tipoHabitacion}/{canthabitaciones}/{nombre}/{apellido}/{dni}/{medioPagoId}/{mailUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reservarHotel(@PathParam("ofertaid") int ofertaid, @PathParam("fDesde") String fDesde,
			@PathParam("fHasta") String fHasta, @PathParam("tipoHabitacion") String tipoHabitacion,
			@PathParam("canthabitaciones") int cantHabitaciones, @PathParam("nombre") String nombre,
			@PathParam("apellido") String apellido, @PathParam("dni") String dni,
			@PathParam("medioPagoId") int medioPagoId, @PathParam("mailUsuario") String mailUsuario) {
		try {
			ReservaDTO reservaDTO=facade.reservarHotel(ofertaid, fDesde, fHasta, tipoHabitacion, cantHabitaciones, nombre, apellido, dni,
					medioPagoId, mailUsuario);
			return Response.ok(new WebResponse(reservaDTO,"SE REGISTRO UNA RESERVA HOTELERA")).build();
		} catch (Exception e) {
			// logearerror(e.getMessage());
			return Response.ok(new WebResponse(e.getMessage(),"ERROR")).build();
		}
	}
	//CAMBIAR POR METODO POST
	@GET
	@Path("reservarpaquete/{ofertaid}/{fDesde}/{fHasta}/{cantPersonas}/{nombre}/{apellido}/{dni}/{medioPagoId}/{mailUsuario}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reservarPaquete(@PathParam("ofertaid") int ofertaid, @PathParam("fDesde") String fDesde,
			@PathParam("fHasta") String fHasta, @PathParam("cantPersonas") int cantPersonas,
			@PathParam("nombre") String nombre, @PathParam("apellido") String apellido, @PathParam("dni") String dni,
			@PathParam("medioPagoId") int medioPagoId, @PathParam("mailUsuario") String mailUsuario) {
		try {
			ReservaDTO reservaDTO=facade.reservarPaquete(ofertaid, fDesde, fHasta, cantPersonas, nombre, apellido, dni, medioPagoId,
					mailUsuario);
			return Response.ok(new WebResponse(reservaDTO,"SE REGISTRO UNA RESERVA DE PAQUETE")).build();
		} catch (Exception e) {
			// logearerror(e.getMessage());
			return Response.ok(new WebResponse(e.getMessage(),"ERROR")).build();
		}
	}

	/*
	//	BORRAR
	 * @GET
		@Path("prestadorautorizado/{codigo_prestador}")
		@Produces(MediaType.APPLICATION_JSON)
		public Response PrestadorAutorizado(@PathParam("codigo_prestador") String codigo_prestador) {
			try {
				boolean estaAutorizado=prestadorEstaAutorizado("8079ec53-de70-43f4-b589-cfead878e688");
				return Response.ok(new WebResponse(estaAutorizado,"boolean")).build();
			} catch (Exception e) {
				// logearerror(e.getMessage());
				return Response.ok(new WebResponse(e.getMessage(),"ERROR")).build();
			}
		}*/
	/*
	private boolean prestadorEstaAutorizado(String codigo_prestador) throws ServiceException {
		SOAPService service = new SOAPService();
		return service.getSOAPPort().estaAutorizado(codigo_prestador);
	}*/
}
