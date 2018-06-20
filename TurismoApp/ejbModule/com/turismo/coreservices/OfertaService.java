package com.turismo.coreservices;

import java.time.LocalDate;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.turismo.dao.DestinoDAO;

import com.turismo.dao.MedioPagoDAO;
import com.turismo.dao.OfertaBloqueDAO;
import com.turismo.dao.OfertaDAO;
import com.turismo.entities.Agencia;
import com.turismo.entities.Destino;
import com.turismo.entities.Establecimiento;
import com.turismo.entities.MedioPago;
import com.turismo.entities.Oferta;
import com.turismo.entities.OfertaTipo;
import com.turismo.entities.TipoHabitacion;
import com.turismo.exceptions.ConversionFechaException;
import com.turismo.exceptions.OfertaHoteleraException;
import com.turismo.exceptions.OfertaPaqueteException;
import com.turismo.qconsumer.mensajes.OfertaHoteleraMensaje;
import com.turismo.qconsumer.mensajes.OfertaPaqueteMensaje;

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
	@EJB
	private BusquedaService busquedaService;

	public void guardarOfertaPaquete(OfertaPaqueteMensaje ofertaPaqueteMensaje)
			throws OfertaPaqueteException, ConversionFechaException {
		int codigo_paquete = ofertaPaqueteMensaje.getId();
		String nombrePaquete = ofertaPaqueteMensaje.getNombre();
		int codigo_ciudadDestino = ofertaPaqueteMensaje.getCiudadDestino().getCodigo_ciudad();
		//No hay q guardar el nombre de la ciudad
		// String nombreCiudadDestino =
		// ofertaPaqueteMensaje.getCiudadDestino().getNombre();
		int cupo = ofertaPaqueteMensaje.getCupo();
		int cantPersonas = ofertaPaqueteMensaje.getCantPersonas();
		// AGENCIA
		int codigo_agencia = ofertaPaqueteMensaje.getAgencia().getId();
		String nombreAgencia = ofertaPaqueteMensaje.getAgencia().getNombre();
		String direccionAgencia = ofertaPaqueteMensaje.getAgencia().getDireccion();
		// Preguntar si es necesario guardar, entiendo que no.
		// String estadoAgencia=ofertaPaqueteMensaje.getEstadoAgencia(); // INACTIVO,
		// ACTIVO
		String foto = ofertaPaqueteMensaje.getFoto();
		String fechaDesde = ofertaPaqueteMensaje.getFechaDesde(); // Ej: 2007-04-05T12:30-02:00
		String fechaHasta = ofertaPaqueteMensaje.getFechaHasta(); // Ej: 2007-04-05T12:30-02:00
		// Preguntar si es necesario guardar, entiendo que no. --> String
		// estado=ofertaPaqueteMensaje.getEstado(); // INACTIVO, ACTIVO
		float precio = ofertaPaqueteMensaje.getPrecio();
		String descripcionPaquete = ofertaPaqueteMensaje.getDescripcion();
		String politicaCancelacion = ofertaPaqueteMensaje.getPoliticas();
		String servicios = ofertaPaqueteMensaje.getServicios();
		String mediosDePago = ofertaPaqueteMensaje.getMediosDePago();
		Destino destino = destinoDAO.buscarDestinoPorCodigo(codigo_ciudadDestino);
		Oferta oferta = ofertaDAO.buscarPorCodigoOferta(codigo_paquete);
		if (destino != null && oferta == null) {
			Agencia agencia = agenciaService.guardarAgencia(nombreAgencia, direccionAgencia, codigo_agencia);
			// convierto la fecha a localdate
			LocalDate fDesdeConverted = busquedaService.convertStringToLocalDate(fechaDesde);
			LocalDate fHastaConverted = busquedaService.convertStringToLocalDate(fechaHasta);
			boolean validarRangoFechaPaquete = busquedaService.validarRangoFechaPaquete(fDesdeConverted,
					fHastaConverted);
			if (validarRangoFechaPaquete) {
				MedioPago medioPagoObject = medioPagoDAO.nuevoMedioPago(mediosDePago);
				OfertaTipo ofertaTipo = OfertaTipo.OFERTA_PAQUETE;
				Oferta nuevaOferta = ofertaDAO.nuevaOfertaPaquete(codigo_paquete, nombrePaquete, cupo, fDesdeConverted,
						fHastaConverted, precio, politicaCancelacion, servicios, destino, descripcionPaquete, foto,
						medioPagoObject, cantPersonas, agencia, ofertaTipo);
				LocalDate fechaPivote = fDesdeConverted;
				// igual a cero significa q son iguales
				// Lo que estoy haciendo es generar los bloques de acuerdo a la cantidad de dias
				int limiteDias = 5000; // limites de dias a procesar para que no procese eternamente
				int count = 0; // contador para prevenir que procese eternamente
				while (fechaPivote.compareTo(fHastaConverted) <= 0 && count < limiteDias) {
					ofertaBloqueDAO.nuevoBloque(nuevaOferta, fechaPivote, cupo);
					fechaPivote = fechaPivote.plusDays(1);
					count++;
				}
				if (count >= limiteDias)
					throw new OfertaPaqueteException(
							"Desde la cola oferta paquete. Se guardaron " + limiteDias + " bloques (SON " + limiteDias
									+ " dias), para no saturar la BD, el resto de los bloques se descarto.");

				if (nuevaOferta == null)
					throw new OfertaPaqueteException(
							"No se pudo grabar la oferta paquete en nuestra base, desde la cola.");
			}
			if (!validarRangoFechaPaquete)
				throw new OfertaPaqueteException(
						"No se pudo guardar la oferta paquete en nuestra base, desde la cola oferta hotelera. La fecha de inicio y fin tiene que se mayor que la fecha actual. Y la fecha de inicio no puede ser mayor a la final.");
		}
		if (destino == null)
			throw new OfertaPaqueteException("No se guardo la oferta paquete id:" + codigo_paquete
					+ " desde la cola porque no existe el destino con el id externo: " + codigo_ciudadDestino);
		if (oferta != null)
			throw new OfertaPaqueteException(
					"Persistencia desde la cola oferta paquete: No se guardo la oferta paquete con id externo: "
							+ codigo_paquete + " porque dicho codigo ya existe en la BD.");
	}

	public void guardarOfertaHotelera(OfertaHoteleraMensaje ofertaHoteleraMensaje)
			throws OfertaHoteleraException, ConversionFechaException {
		int codigoOfertaHotelera = ofertaHoteleraMensaje.getIdOfertaHotelera();
		String nombreOfertaHotelera = ofertaHoteleraMensaje.getNombre();
		float precio = ofertaHoteleraMensaje.getPrecio();// precio de la habitacion
		int cupo = ofertaHoteleraMensaje.getCupo();
		String mediosDePago = ofertaHoteleraMensaje.getMediosDePago();
		// Establecimiento
		int codigo_Establecimiento = ofertaHoteleraMensaje.getEstablecimiento().getId();
		// Entiendo que no hace falta guardarlo
		// String uidBackOffice = ofertaHoteleraMensaje.getEstablecimiento().getUid();
		// // Id recibido del backoffice
		String nombreEstablecimiento = ofertaHoteleraMensaje.getEstablecimiento().getNombre();
		String direccionEstablecimiento = ofertaHoteleraMensaje.getEstablecimiento().getDireccion();
		int codigo_ciudad = ofertaHoteleraMensaje.getEstablecimiento().getCiudad().getCodigo_ciudad();
		int codigo_hotel = ofertaHoteleraMensaje.getEstablecimiento().getHotel().getId();
		String nombreHotel = ofertaHoteleraMensaje.getEstablecimiento().getHotel().getNombre();
		String urlFotoHotel = ofertaHoteleraMensaje.getEstablecimiento().getHotel().getFotoHotel();
		// Establecimiento
		String descripcionEstablecimiento = ofertaHoteleraMensaje.getEstablecimiento().getDescripcion();
		String mapaLatitud = ofertaHoteleraMensaje.getEstablecimiento().getMapa().getLat();
		String mapaLongitud = ofertaHoteleraMensaje.getEstablecimiento().getMapa().getLon();
		String urlFotoEstablecimiento = ofertaHoteleraMensaje.getEstablecimiento().getFotoestablecimiento();
		int cantEstrellas = ofertaHoteleraMensaje.getEstablecimiento().getEstrellas();// de 1 a 5
		// Campos oferta hotelera
		String fechaDesde = ofertaHoteleraMensaje.getFechaDesde();// Ej: 2007-04-05T12:30-02:00
		String fechaHasta = ofertaHoteleraMensaje.getFechaHasta();// Ej: 2007-04-05T12:30-02:00
		String politicaCancelacion = ofertaHoteleraMensaje.getPoliticas();// Texto con las politicas
		String servicios = ofertaHoteleraMensaje.getServicios();
		Destino destino = destinoDAO.buscarDestinoPorCodigo(codigo_ciudad);
		Oferta oferta = ofertaDAO.buscarPorCodigoOferta(codigoOfertaHotelera);
		TipoHabitacion tipoHabitacion = TipoHabitacion.valueOf(ofertaHoteleraMensaje.getTipoHabitacion()); // SIMPLE, DOBLE, TRIPLE
		if (destino != null && oferta == null && tipoHabitacion!=null) {
			Establecimiento establecimiento = establecimientoService.guardarEstablecimiento(nombreEstablecimiento,
					direccionEstablecimiento, destino.getNombre(), descripcionEstablecimiento, cantEstrellas,
					mapaLatitud, mapaLongitud, codigo_Establecimiento, codigo_hotel, nombreHotel, urlFotoHotel);
			// convierto la fecha a localdate
			LocalDate fDesdeConverted = busquedaService.convertStringToLocalDate(fechaDesde);
			LocalDate fHastaConverted = busquedaService.convertStringToLocalDate(fechaHasta);
			boolean validarRangoFechaHotelera = busquedaService.validarRangoFechaHotelera(fDesdeConverted,
					fHastaConverted);
			if (validarRangoFechaHotelera) {
				// Genero medios de pago
				MedioPago medioPagoObject = medioPagoDAO.nuevoMedioPago(mediosDePago);
				OfertaTipo ofertaTipo = OfertaTipo.OFERTA_HOTELERA;
				Oferta nuevaOferta = ofertaDAO.nuevaOfertaHotelera(codigoOfertaHotelera, nombreOfertaHotelera, cupo,
						fDesdeConverted, fHastaConverted, precio, tipoHabitacion, politicaCancelacion, servicios,
						destino, urlFotoEstablecimiento, medioPagoObject, establecimiento, ofertaTipo);
				LocalDate fechaPivote = fDesdeConverted;
				// igual a cero significa q son iguales
				// Lo que estoy haciendo es generar los bloques de acuerdo a la cantidad de dias
				int count = 0; // contador para prevenir que procese eternamente
				int limiteDias = 5000; // limites de dias a procesar para que no procese eternamente
				while (nuevaOferta != null && fechaPivote.compareTo(fHastaConverted) <= 0 && count < limiteDias) {
					ofertaBloqueDAO.nuevoBloque(nuevaOferta, fechaPivote, cupo);
					fechaPivote = fechaPivote.plusDays(1);
					count++;
				}
				if (count >= limiteDias)
					throw new OfertaHoteleraException(
							"Desde la cola oferta hotelera. Se guardaron " + limiteDias + " bloques (SON " + limiteDias
									+ " dias), para no saturar la BD, el resto de los bloques se descarto.");
				if (nuevaOferta == null)
					throw new OfertaHoteleraException(
							"No se pudo grabar la oferta hotelera en nuestra base desde la cola.");
				if (!validarRangoFechaHotelera)
					throw new OfertaHoteleraException(
							"No se pudo guardar la oferta hotelera en nuestra base, desde la cola oferta hotelera. La fecha de fin tiene que se mayor que la fecha actual. Y la fecha de inicio no puede ser mayor a la final.");
			}
		}
		if (destino == null)
			throw new OfertaHoteleraException("No se guardo la oferta hotelera id:" + codigoOfertaHotelera
					+ " desde la cola porque no existe el destino con el id externo: " + codigo_ciudad);
		if (oferta != null)
			throw new OfertaHoteleraException(
					"Persistencia desde la cola oferta hotelera: No se guardo la oferta hotelera con codigo: "
							+ codigoOfertaHotelera + ", porque dicho codigo ya existe en la BD.");
		if (tipoHabitacion == null)
			throw new OfertaHoteleraException(
					"Problema para parsear el tipo de habitacion consumido de la cola de oferta hotelera. El valor que no puede ser parseado es : "
							+ ofertaHoteleraMensaje.getTipoHabitacion());
	}
}
