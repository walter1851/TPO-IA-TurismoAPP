package com.turismo.coreservices;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.turismo.dao.DestinoDAO;
import com.turismo.dao.HotelDAO;
import com.turismo.dao.MedioPagoDAO;
import com.turismo.dao.OfertaBloqueDAO;
import com.turismo.dao.OfertaDAO;
import com.turismo.entities.Agencia;
import com.turismo.entities.Destino;
import com.turismo.entities.Establecimiento;
import com.turismo.entities.Hotel;
import com.turismo.entities.MedioPago;
import com.turismo.entities.Oferta;
import com.turismo.entities.OfertaTipo;
import com.turismo.integraciones.qconsumer.OfertaHoteleraMensaje;
import com.turismo.integraciones.qconsumer.OfertaPaqueteMensaje;

@Stateless
@LocalBean
public class OfertaService {
	@EJB
	private OfertaDAO ofertaDAO;
	@EJB
	private DestinoDAO destinoDAO;
	@EJB
	private OfertaBloqueDAO ofertaBloqueDAO;
	@EJB
	private EstablecimientoService establecimientoService;
	@EJB
	private AgenciaService agenciaService;
	@EJB
	private MedioPagoDAO medioPagoDAO;

	public void guardarOfertaPaquete(OfertaPaqueteMensaje ofertaPaqueteMensaje) {
		 String idPaquete=ofertaPaqueteMensaje.getIdPaquete();
		 String nombrePaquete=ofertaPaqueteMensaje.getNombrePaquete();
		 String idCiudadDestino=ofertaPaqueteMensaje.getIdCiudadDestino();
		 String nombreCiudadDestino=ofertaPaqueteMensaje.getNombreCiudadDestino();
		 int cupo=ofertaPaqueteMensaje.getCupo();
		 int cantPersonas=ofertaPaqueteMensaje.getCantPersonas();
		// AGENCIA
		 String idAgencia=ofertaPaqueteMensaje.getIdAgencia();
		 String nombreAgencia=ofertaPaqueteMensaje.getNombreAgencia();
		 String direccionAgencia=ofertaPaqueteMensaje.getDireccionAgencia();
		 String estadoAgencia=ofertaPaqueteMensaje.getEstadoAgencia(); // INACTIVO, ACTIVO
		// Oferta Paquete
		 String foto=ofertaPaqueteMensaje.getFoto();
		 String fechaDesde=ofertaPaqueteMensaje.getFechaDesde(); // Ej: 2007-04-05T12:30-02:00
		 String fechaHasta=ofertaPaqueteMensaje.getFechaHasta(); // Ej: 2007-04-05T12:30-02:00
		 String estado=ofertaPaqueteMensaje.getEstado(); // INACTIVO, ACTIVO
		 float precio=ofertaPaqueteMensaje.getPrecio();
		 String descripcion=ofertaPaqueteMensaje.getDescripcion();
		 String politicaCancelacion=ofertaPaqueteMensaje.getPoliticaCancelacion();
		 String servicios=ofertaPaqueteMensaje.getServicios();
		 List<String> mediosDePagoStrings=ofertaPaqueteMensaje.getMediosDePago();
		Destino destino = destinoDAO.buscarDestinoPorNombre(nombreCiudadDestino);
		Agencia agencia = agenciaService.guardarAgencia(idAgencia, direccionAgencia, nombreAgencia);
		//convierto la fecha a localdatetime
		LocalDateTime fDesdeConverted=convertStringToLocalDateTime(fechaDesde);
		LocalDateTime fHastaConverted=convertStringToLocalDateTime(fechaHasta);
		//Genero medios de pago
		List<MedioPago> mediosDePago=new ArrayList<MedioPago>();
		MedioPago medioPagoObject=null;
		for (String medioPago : mediosDePagoStrings) {
			medioPagoObject=medioPagoDAO.nuevoMedioPago(medioPago, medioPago);
			mediosDePago.add(medioPagoObject);
		}
			//Genero nueva oferta tipo si no existe
		OfertaTipo nuevaOfertaTipo=new OfertaTipo();
		nuevaOfertaTipo.setNombre("ofertaHotelera");
		Oferta nuevaOferta = ofertaDAO.nuevaOfertaPaquete(nombrePaquete, cupo, fDesdeConverted, fHastaConverted, precio,
				politicaCancelacion, servicios,destino, foto,medioPagoObject, cantPersonas, agencia, nuevaOfertaTipo);
		LocalDateTime fechaPivote = fDesdeConverted;
		// igual a cero significa q son iguales
		// Lo que estoy haciendo es generar los bloques de acuerdo a la cantidad de dias
		int count=0; //contador para prevenir que procese eternamente
		while (fechaPivote.compareTo(fHastaConverted) == 0 && count<20) {
			ofertaBloqueDAO.nuevoBloque(nuevaOferta, fechaPivote, cupo);
			fechaPivote.plusDays(1);
			count++;
		}
	}
	private LocalDateTime convertStringToLocalDateTime(String stringFecha) {
		//Estamos validando que la fecha tenga el formato correcto
		//ejemplo 2018-06-20T12:30-02:00
		DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
		//ISO_OFFSET_DATE_TIME	Date Time with Offset	2011-12-03T10:15:30+01:00'
		LocalDateTime dateTime = LocalDateTime.parse(stringFecha, formatter);
		return dateTime;
	}
	public void guardarOfertaHotelera(OfertaHoteleraMensaje ofertaHoteleraMensaje) {
		String idOfertaHotelera=ofertaHoteleraMensaje.getIdOfertaHotelera();
		String nombreOfertaHotelera=ofertaHoteleraMensaje.getNombreOfertaHotelera();
		float precio=ofertaHoteleraMensaje.getPrecio();// precio de la habitacion
		int cupo=ofertaHoteleraMensaje.getCupo();
		// FALTA CANT PERSONAS?
		int cantPeronas=3;
		List<String> mediosDePagoStrings=ofertaHoteleraMensaje.getMediosDePago();
		String tipoHabitacion=ofertaHoteleraMensaje.getTipoHabitacion(); // SIMPLE, DOBLE, TRIPLE
		// Establecimiento
		String idEstablecimiento=ofertaHoteleraMensaje.getIdEstablecimiento();
		String uidBackOffice=ofertaHoteleraMensaje.getUidBackOffice(); // Id recibido del backoffice
		String nombreEstablecimiento=ofertaHoteleraMensaje.getNombreEstablecimiento();
		String direccionEstablecimiento=ofertaHoteleraMensaje.getDireccionEstablecimiento();
		String idCiudad=ofertaHoteleraMensaje.getIdCiudad();
		String nombreCiudad=ofertaHoteleraMensaje.getNombreCiudad();
		// Hotel
		String idHotel=ofertaHoteleraMensaje.getIdHotel();
		String nombreHotel=ofertaHoteleraMensaje.getNombreHotel();
		String urlFotoHotel=ofertaHoteleraMensaje.getUrlFotoHotel();
		// Establecimiento
		String descripcionEstablecimiento=ofertaHoteleraMensaje.getDescripcionEstablecimiento();
		String mapaLatitud=ofertaHoteleraMensaje.getMapaLatitud();
		String mapaLongitud=ofertaHoteleraMensaje.getMapaLongitud();
		String urlFotoEstablecimiento=ofertaHoteleraMensaje.getUrlFotoEstablecimiento();// Esto es una foto sola, no es un array
		int cantEstrellas=ofertaHoteleraMensaje.getCantEstrellas();// de 1 a 5
		// Campos oferta hotelera
		String fechaDesde=ofertaHoteleraMensaje.getFechaDesde();// Ej: 2007-04-05T12:30-02:00
		String fechaHasta=ofertaHoteleraMensaje.getFechaHasta();// Ej: 2007-04-05T12:30-02:00
		String politicaCancelacion=ofertaHoteleraMensaje.getPoliticaCancelacion();// Texto con las politicas
		String servicios=ofertaHoteleraMensaje.getServicios();
		Destino destino = destinoDAO.nuevoDestino(nombreCiudad);
		Establecimiento establecimiento= establecimientoService.guardarEstablecimiento(nombreEstablecimiento, direccionEstablecimiento, nombreCiudad, nombreCiudad, descripcionEstablecimiento, Integer.toString(cantEstrellas), "lat: "+mapaLatitud+" long: "+mapaLongitud, idEstablecimiento, idHotel,nombreHotel,urlFotoHotel);
		//convierto la fecha a localdatetime
		LocalDateTime fDesdeConverted=convertStringToLocalDateTime(fechaDesde);
		LocalDateTime fHastaConverted=convertStringToLocalDateTime(fechaHasta);
		//Genero medios de pago
		List<MedioPago> mediosDePago=new ArrayList<MedioPago>();
		MedioPago medioPagoObject=null;
		for (String medioPago : mediosDePagoStrings) {
			medioPagoObject=medioPagoDAO.nuevoMedioPago(medioPago, medioPago);
			mediosDePago.add(medioPagoObject);
		}
			//Genero nueva oferta tipo si no existe
		OfertaTipo nuevaOfertaTipo=new OfertaTipo();
		nuevaOfertaTipo.setNombre("ofertaHotelera");
		Oferta nuevaOferta = ofertaDAO.nuevaOfertaHotelera(nombreOfertaHotelera, cupo, fDesdeConverted, fHastaConverted, precio,
				tipoHabitacion, politicaCancelacion, servicios, destino, urlFotoEstablecimiento, medioPagoObject, cantPeronas, establecimiento,
				nuevaOfertaTipo);
		LocalDateTime fechaPivote = fDesdeConverted;
		// igual a cero significa q son iguales
		// Lo que estoy haciendo es generar los bloques de acuerdo a la cantidad de dias
		int count=0; //contador para prevenir que procese eternamente
		while (fechaPivote.compareTo(fHastaConverted) == 0 && count<20) {
			ofertaBloqueDAO.nuevoBloque(nuevaOferta, fechaPivote, cupo);
			fechaPivote.plusDays(1);
			count++;
		}
	}
}
