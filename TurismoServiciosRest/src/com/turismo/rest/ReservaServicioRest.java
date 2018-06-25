package com.turismo.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.turismo.controller.ControllerService;
import com.turismo.dto.ReservaDTO;
import com.turismo.rest.response.WebResponse;


@Path("/reserva")
@Stateless
public class ReservaServicioRest {
	@EJB
	private ControllerService facade;
	//@EJB
	//private BackOfficeLogging loggingBackOffice;
	
	//CAMBIAR POR METODO POST
	@POST
	@Path("reservarhotel")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reservarHotel(String ofertaid, String fDesde,
			String fHasta, String tipoHabitacion,
		    String cantHabitaciones, String nombre,
		    String apellido, String dni,String medioPagoId, String mailUsuario) {
		try {
			ReservaDTO reservaDTO=facade.reservarHotel(Integer.parseInt(ofertaid), fDesde, fHasta, tipoHabitacion, Integer.parseInt(cantHabitaciones), nombre, apellido, dni,
					Integer.parseInt(medioPagoId), mailUsuario);
			return Response.ok(new WebResponse(reservaDTO,"SE REGISTRO UNA RESERVA HOTELERA")).build();
		} catch (Exception e) {
			// logearerror(e.getMessage());
			return Response.ok(new WebResponse(e.getMessage(),"ERROR")).build();
		}
	}
	//CAMBIAR POR METODO POST
	@POST
	@Path("reservarpaquete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response reservarPaquete(String ofertaid, String fDesde,
			String fHasta, String cantPersonas, String nombre, String apellido, String dni,
			String medioPagoId, String mailUsuario) {
		try {
			ReservaDTO reservaDTO=facade.reservarPaquete(Integer.parseInt(ofertaid), fDesde, fHasta, Integer.parseInt(cantPersonas), nombre, apellido, dni, Integer.parseInt(medioPagoId),
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
