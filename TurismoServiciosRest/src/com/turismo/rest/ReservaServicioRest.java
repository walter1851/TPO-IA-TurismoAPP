package com.turismo.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.turismo.backoffice.logging.BackOfficeLogging;
import com.turismo.backoffice.logging.LoggingAccion;
import com.turismo.controller.ControllerService;
import com.turismo.dto.ReservaDTO;
import com.turismo.rest.mensajes.SolicitudReservaHotelera;
import com.turismo.rest.mensajes.SolicitudReservaPaquete;
import com.turismo.rest.mensajes.WebResponse;

@Path("/reserva")
@Stateless
public class ReservaServicioRest {
	@EJB
	private ControllerService facade;
	@EJB
	private BackOfficeLogging loggingBackOffice;

	@POST
	@Path("reservarhotel")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response reservarHotel(SolicitudReservaHotelera SolicitudReservaHotelera) {
		try {
			ReservaDTO reservaDTO = facade.reservarHotel(SolicitudReservaHotelera.getOfertaid(),
					SolicitudReservaHotelera.getfDesde(), SolicitudReservaHotelera.getfHasta(),
					SolicitudReservaHotelera.getTipoHabitacion(), SolicitudReservaHotelera.getCantTotalPersonas(),
					SolicitudReservaHotelera.getNombre(), SolicitudReservaHotelera.getApellido(),
					SolicitudReservaHotelera.getDni(), SolicitudReservaHotelera.getMedioPagoId(),
					SolicitudReservaHotelera.getMailUsuario());
			this.loggingBackOffice.info(LoggingAccion.RESERVA_DE_HOTEL);
			return Response.ok(new WebResponse(reservaDTO, "SE REGISTRO UNA RESERVA HOTELERA")).build();
		} catch (Exception e) {
			String nombreException=e.getClass().getName();
			System.out.println(nombreException + " -> " + e.getMessage());
			WebResponse webResponse = new WebResponse(e.getMessage(), nombreException);
			//return Response.status(404).entity(webResponse).build();
			return Response.ok(webResponse).build();
		}
	}

	@POST
	@Path("reservarpaquete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response reservarPaquete(SolicitudReservaPaquete solicitudReservaPaquete) {
		try {
			ReservaDTO reservaDTO = facade.reservarPaquete(solicitudReservaPaquete.getOfertaid(),
					solicitudReservaPaquete.getfDesde(), solicitudReservaPaquete.getfHasta(),
					solicitudReservaPaquete.getCantPersonas(), solicitudReservaPaquete.getNombre(),
					solicitudReservaPaquete.getApellido(), solicitudReservaPaquete.getDni(),
					solicitudReservaPaquete.getMedioPagoId(), solicitudReservaPaquete.getMailUsuario());
			this.loggingBackOffice.info(LoggingAccion.RESERVA_DE_PAQUETE);
			return Response.ok(new WebResponse(reservaDTO, "SE REGISTRO UNA RESERVA DE PAQUETE")).build();
		} catch (Exception e) {
			//Response.status(404).entity(yourMessage).type( getAcceptType()).build();
			// (new WebResponse(e.getMessage(), "EXCEPTION")).build();
			String nombreException=e.getClass().getName();
			System.out.println(nombreException + " -> " + e.getMessage());
			WebResponse webResponse = new WebResponse(e.getMessage(), nombreException);
			//return Response.status(404).entity(webResponse).build();
			return Response.ok(webResponse).build();
		}
	}

	@GET
	@Path("prestadorautorizado/{codigo_prestador}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response prestadorEstaAutorizado(@PathParam("codigo_prestador") String codigo_prestador) {
		try {
			boolean estaAutorizado = facade.prestadorEstaAutorizado(codigo_prestador);
			return Response.ok(new WebResponse(estaAutorizado, "prestador autorizado?")).build();
		} catch (Exception e) {
			String nombreException=e.getClass().getName();
			System.out.println(nombreException + " -> " + e.getMessage());
			WebResponse webResponse = new WebResponse(e.getMessage(), nombreException);
			//return Response.status(404).entity(webResponse).build();
			return Response.ok(webResponse).build();
		}
	}
}
