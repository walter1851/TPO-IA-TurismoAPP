package com.turismo.coreservices;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import com.turismo.entities.Estado;
import com.turismo.entities.MedioPago;
import com.turismo.entities.Oferta;
import com.turismo.entities.OfertaTipo;
import com.turismo.exceptions.OfertaHoteleraException;
import com.turismo.exceptions.OfertaPaqueteException;
import com.turismo.integracion.qconsumer.mensajes.OfertaHoteleraMensaje;
import com.turismo.integracion.qconsumer.mensajes.OfertaPaqueteMensaje;

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

	public void guardarOfertaPaquete(OfertaPaqueteMensaje ofertaPaqueteMensaje) throws OfertaPaqueteException {
		try {
			int idPaquete = ofertaPaqueteMensaje.getId();
			String nombrePaquete = ofertaPaqueteMensaje.getNombre();
			int codigo_ciudadDestino = ofertaPaqueteMensaje.getCiudadDestino().getCodigo_ciudad();
			// String nombreCiudadDestino =
			// ofertaPaqueteMensaje.getCiudadDestino().getNombre();
			int cupo = ofertaPaqueteMensaje.getCupo();
			int cantPersonas = ofertaPaqueteMensaje.getCantPersonas();
			// AGENCIA
			int idAgencia = ofertaPaqueteMensaje.getAgencia().getId();
			String nombreAgencia = ofertaPaqueteMensaje.getAgencia().getNombre();
			String direccionAgencia = ofertaPaqueteMensaje.getAgencia().getDireccion();
			// Preguntar si es necesario guardar, entiendo que no.
			// String estadoAgencia=ofertaPaqueteMensaje.getEstadoAgencia(); // INACTIVO,
			// ACTIVO
			// Oferta Paquete
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
			if (destino == null)
				throw new OfertaPaqueteException(
						"No se guardaron las ofertas paquetes de la cola. No existe el destino con el id externo: "
								+ codigo_ciudadDestino);
			else {
				Agencia agencia = agenciaService.guardarAgencia(nombreAgencia, direccionAgencia, idAgencia);
				// convierto la fecha a localdatetime
				LocalDate fDesdeConverted = convertStringToLocalDate(fechaDesde);
				LocalDate fHastaConverted = convertStringToLocalDate(fechaHasta);
				MedioPago medioPagoObject = medioPagoDAO.nuevoMedioPago(mediosDePago);

				OfertaTipo ofertaTipo = OfertaTipo.OFERTA_PAQUETE;
				Oferta nuevaOferta = ofertaDAO.nuevaOfertaPaquete(nombrePaquete, cupo, fDesdeConverted, fHastaConverted,
						precio, politicaCancelacion, servicios, destino, descripcionPaquete, foto, medioPagoObject,
						cantPersonas, agencia, ofertaTipo);
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
		} catch (NullPointerException npe) {
			throw new OfertaPaqueteException(
					"Se consumieron datos de la cola de oferta paquete y cuando se intento guardar en nuestra BD genero un NullPointerException. Detalle: "
							+ npe.getMessage());
		}
	}

	private LocalDate convertStringToLocalDate(String stringFecha) {
		// Estamos validando que la fecha tenga el formato correcto
		// ejemplo 2018-06-20T12:30-02:00
		DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
		// ISO_OFFSET_DATE_TIME Date Time with Offset 2011-12-03T10:15:30+01:00'
		LocalDate dateTime = LocalDate.parse(stringFecha, formatter);
		return dateTime;
	}

	public void guardarOfertaHotelera(OfertaHoteleraMensaje ofertaHoteleraMensaje) throws OfertaHoteleraException {
		try {
			int idOfertaHotelera = ofertaHoteleraMensaje.getIdOfertaHotelera();
			String nombreOfertaHotelera = ofertaHoteleraMensaje.getNombre();
			float precio = ofertaHoteleraMensaje.getPrecio();// precio de la habitacion
			int cupo = ofertaHoteleraMensaje.getCupo();
			String mediosDePago = ofertaHoteleraMensaje.getMediosDePago();
			String tipoHabitacion = ofertaHoteleraMensaje.getTipoHabitacion(); // SIMPLE, DOBLE, TRIPLE
			// Establecimiento
			int idEstablecimiento = ofertaHoteleraMensaje.getEstablecimiento().getId();
			String uidBackOffice = ofertaHoteleraMensaje.getEstablecimiento().getUid(); // Id recibido del backoffice
			String nombreEstablecimiento = ofertaHoteleraMensaje.getEstablecimiento().getNombre();
			String direccionEstablecimiento = ofertaHoteleraMensaje.getEstablecimiento().getDireccion();
			int idCiudad = ofertaHoteleraMensaje.getEstablecimiento().getCiudad().getCodigo_ciudad();
			int idHotel = ofertaHoteleraMensaje.getEstablecimiento().getHotel().getId();
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
			Destino destino = destinoDAO.buscarDestinoPorCodigo(idCiudad);
			if (destino == null)
				throw new OfertaHoteleraException(
						"No se guardaron las ofertas de la cola. No existe el destino con el id: " + idCiudad);
			else {
				Establecimiento establecimiento = establecimientoService.guardarEstablecimiento(nombreEstablecimiento,
						direccionEstablecimiento, destino.getNombre(), Estado.ACTIVO, descripcionEstablecimiento,
						cantEstrellas, mapaLatitud, mapaLongitud, idEstablecimiento, idHotel, nombreHotel,
						urlFotoHotel);
				// convierto la fecha a localdate
				LocalDate fDesdeConverted = convertStringToLocalDate(fechaDesde);
				LocalDate fHastaConverted = convertStringToLocalDate(fechaHasta);
				// Genero medios de pago
				MedioPago medioPagoObject = medioPagoDAO.nuevoMedioPago(mediosDePago);
				OfertaTipo ofertaTipo = OfertaTipo.OFERTA_HOTELERA;
				Oferta nuevaOferta = ofertaDAO.nuevaOfertaHotelera(nombreOfertaHotelera, cupo, fDesdeConverted,
						fHastaConverted, precio, tipoHabitacion, politicaCancelacion, servicios, destino,
						urlFotoEstablecimiento, medioPagoObject, establecimiento, ofertaTipo);
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
							"Desde la cola oferta hotelera. Se guardaron "+ limiteDias +" bloques (SON "+limiteDias+" dias), para no saturar la BD, el resto de los bloques se descarto.");

				if (nuevaOferta == null)
					throw new OfertaHoteleraException(
							"No se pudo grabar la oferta hotelera en nuestra base desde la cola.");
			}
		} catch (NullPointerException npe) {
			throw new OfertaHoteleraException(
					"Se consumieron datos de la cola de oferta HOTELERA y cuando se intento guardar en nuestra BD genero un NullPointerException. Detalle: "
							+ npe.getMessage());
		}
	}
}
