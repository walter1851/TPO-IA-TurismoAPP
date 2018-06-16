package com.turismo.testing.borrar;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.turismo.controller.ControllerService;
import com.turismo.dto.OfertaDTO;
import com.turismo.exceptions.OfertaPaqueteException;
import com.turismo.serviciorest.response.WebResponse;

@Path("/backoffice")
@Stateless
public class ProducerOfertaPaqueteRest {
	@EJB
	private ProducerOfertaPaqueteQ testingQueue;

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
	@Path("ofertapaquete/grabar/{idOfertaHotelera}/{nombreOfertaHotelera}/{precio}/{cupo}/{medioDePago}/{tipoHabitacion}/{idEstablecimiento}/{uidBackOffice}/{nombreEstablecimiento}/{direccionEstablecimiento}/{idCiudad}/{nombreCiudad}/{idHotel}/{nombreHotel}/{urlFotoHotel}/{descripcionEstablecimiento}/{mapaLatitud}/{mapaLongitud}/{urlFotoEstablecimiento}/{cantEstrellas}/{fechaDesde}/{fechaHasta}/{politicaCancelacion}/{servicios}")
	@Produces(MediaType.APPLICATION_JSON)
	
	/*
	 * 
		private String idPaquete;
	private String nombrePaquete;
	private String idCiudadDestino;
	private String nombreCiudadDestino;
	private int cupo;
	private int cantPersonas;
	// AGENCIA
	private String idAgencia;
	private String nombreAgencia;
	private String direccionAgencia;
	private String estadoAgencia; // INACTIVO, ACTIVO
	// Oferta Paquete
	private String foto;
	private String fechaDesde; // Ej: 2007-04-05T12:30-02:00
	private String fechaHasta; // Ej: 2007-04-05T12:30-02:00
	private String estado; // INACTIVO, ACTIVO
	private float precio;
	private String descripcion;
	private String politicaCancelacion;
	private String servicios;
	private List<String> mediosDePago;
	 */
	public Response buscarOfertaPaquete(@PathParam("idOfertaPauquete") String idOfertaPauquete,
			@PathParam("nombrePaquete") String nombrePaquete, @PathParam("idCiudadDestino") String idCiudadDestino,
			@PathParam("nombreCiudadDestino") String nombreCiudadDestino,@PathParam("cupo") int cupo,
			@PathParam("cantPersonas") int cantPersonas, @PathParam("idAgencia") String idAgencia,
			@PathParam("nombreAgencia") String nombreAgencia,
			@PathParam("direccionAgencia") String direccionAgencia, @PathParam("estadoAgencia") String estadoAgencia,
			@PathParam("foto") String foto,
			@PathParam("fechaDesde") String fechaDesde, @PathParam("fechaHasta") String fechaHasta,
			@PathParam("estado") String estado, @PathParam("precio") float precio,
			@PathParam("descripcion") String descripcion, @PathParam("politicaCancelacion") String politicaCancelacion,
			@PathParam("servicios") String servicios,
			@PathParam("mediosDePago") String mediosDePago) {
		try {
			testingQueue.sendMessage(idOfertaPauquete, nombrePaquete, idCiudadDestino, nombreCiudadDestino, cupo, cantPersonas,
					idAgencia, nombreAgencia, direccionAgencia, estadoAgencia, foto,
					fechaDesde, fechaHasta, estado, precio, descripcion, politicaCancelacion,
					servicios, mediosDePago);
			return Response.ok(new WebResponse(true, "Se grabo en la cola la oferta hotelera")).build();
		} catch (Exception e) {
			// logearerror(e.getMessage());
			return Response.ok(new WebResponse(false, e.getMessage())).build();
		}
	}
}
