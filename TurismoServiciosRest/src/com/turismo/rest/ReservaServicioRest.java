package com.turismo.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
	// @EJB
	// private BackOfficeLogging loggingBackOffice;

	@POST
	@Path("reservarhotel")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response reservarHotel(SolicitudReservaHotelera SolicitudReservaHotelera) {
		try {
			ReservaDTO reservaDTO = facade.reservarHotel(SolicitudReservaHotelera.getOfertaid(),
					SolicitudReservaHotelera.getfDesde(), SolicitudReservaHotelera.getfHasta(),
					SolicitudReservaHotelera.getTipoHabitacion(), SolicitudReservaHotelera.getCantHabitaciones(),
					SolicitudReservaHotelera.getNombre(), SolicitudReservaHotelera.getApellido(),
					SolicitudReservaHotelera.getDni(), SolicitudReservaHotelera.getMedioPagoId(),
					SolicitudReservaHotelera.getMailUsuario());
			return Response.ok(new WebResponse(reservaDTO, "SE REGISTRO UNA RESERVA HOTELERA")).build();
		} catch (Exception e) {
			// logearerror(e.getMessage());
			return Response.ok(new WebResponse(e.getMessage(), "ERROR")).build();
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
			return Response.ok(new WebResponse(reservaDTO, "SE REGISTRO UNA RESERVA DE PAQUETE")).build();
		} catch (Exception e) {
			// logearerror(e.getMessage());
			return Response.ok(new WebResponse(e.getMessage(), "ERROR")).build();
		}
	}
}
