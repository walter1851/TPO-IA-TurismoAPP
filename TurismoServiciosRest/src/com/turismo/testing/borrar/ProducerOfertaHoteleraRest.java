package com.turismo.testing.borrar;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.turismo.serviciorest.response.WebResponse;
import com.turismo.testing.qproducer.ProducerOfertaHoteleraQ;

@Path("/backoffice")
@Stateless
public class ProducerOfertaHoteleraRest {
	@EJB
	private ProducerOfertaHoteleraQ testingQueue;

	/*
	 * // Campos oferta hotelera private int idOfertaHotelera; private String
	 * nombreOfertaHotelera; private float precio;// precio de la habitacion private
	 * int cupo; // FALTA CANT PERSONAS? private List<String> mediosDePago; private
	 * String tipoHabitacion; // SIMPLE, DOBLE, TRIPLE // Establecimiento private
	 * int idEstablecimiento; private String uidBackOffice; // Id recibido del
	 * backoffice private String nombreEstablecimiento; private String
	 * direccionEstablecimiento; private int idCiudad; private String nombreCiudad;
	 * // Hotel private int idHotel; private String nombreHotel; private String
	 * urlFotoHotel; // Establecimiento private String descripcionEstablecimiento;
	 * private String mapaLatitud; private String mapaLongitud; private String
	 * urlFotoEstablecimiento;// Esto es una foto sola, no es un array private int
	 * cantEstrellas;// de 1 a 5 // Campos oferta hotelera private Date
	 * fechaDesde;// Ej: 2007-04-05T12:30-02:00 private Date fechaHasta;// Ej:
	 * 2007-04-05T12:30-02:00 private String politicaCancelacion;// Texto con las
	 * politicas private List<String> servicios;
	 */
	@GET
	@Path("ofertahotelera/grabar/{idOfertaHotelera}/{nombreOfertaHotelera}/{precio}/{cupo}/{medioDePago}/{tipoHabitacion}/{idEstablecimiento}/{uidBackOffice}/{nombreEstablecimiento}/{direccionEstablecimiento}/{idCiudad}/{idHotel}/{nombreHotel}/{urlFotoHotel}/{descripcionEstablecimiento}/{mapaLatitud}/{mapaLongitud}/{urlFotoEstablecimiento}/{cantEstrellas}/{fechaDesde}/{fechaHasta}/{politicaCancelacion}/{servicios}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarOfertaHotelera(@PathParam("idOfertaHotelera") int idOfertaHotelera,
			@PathParam("nombreOfertaHotelera") String nombreOfertaHotelera, @PathParam("precio") float precio,
			@PathParam("cupo") int cupo, @PathParam("medioDePago") String medioDePago,
			@PathParam("tipoHabitacion") String tipoHabitacion, @PathParam("idEstablecimiento") int idEstablecimiento,
			@PathParam("uidBackOffice") String uidBackOffice,
			@PathParam("nombreEstablecimiento") String nombreEstablecimiento,
			@PathParam("direccionEstablecimiento") String direccionEstablecimiento, @PathParam("idCiudad") int idCiudad,
			@PathParam("idHotel") int idHotel, @PathParam("nombreHotel") String nombreHotel,
			@PathParam("urlFotoHotel") String urlFotoHotel,
			@PathParam("descripcionEstablecimiento") String descripcionEstablecimiento,
			@PathParam("mapaLatitud") String mapaLatitud, @PathParam("mapaLongitud") String mapaLongitud,
			@PathParam("urlFotoEstablecimiento") String urlFotoEstablecimiento,
			@PathParam("cantEstrellas") int cantEstrellas, @PathParam("fechaDesde") String fechaDesde,
			@PathParam("fechaHasta") String fechaHasta, @PathParam("politicaCancelacion") String politicaCancelacion,
			@PathParam("servicios") String servicios) {
		try {
			testingQueue.sendMessage(idOfertaHotelera, nombreOfertaHotelera, precio, cupo, medioDePago, tipoHabitacion,
					idEstablecimiento, uidBackOffice, nombreEstablecimiento, direccionEstablecimiento, idCiudad,
					idHotel, nombreHotel, urlFotoHotel, descripcionEstablecimiento, mapaLatitud, mapaLongitud,
					urlFotoEstablecimiento, cantEstrellas, fechaDesde, fechaHasta, politicaCancelacion, servicios);
			return Response.ok(new WebResponse(true, "Se grabo en la cola la oferta hotelera")).build();
		} catch (Exception e) {
			// logearerror(e.getMessage());
			return Response.ok(new WebResponse(false, e.getMessage())).build();
		}
	}
}
