package com.turismo.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.turismo.entities.Agencia;
import com.turismo.entities.Destino;
import com.turismo.entities.Establecimiento;
import com.turismo.entities.MedioPago;
import com.turismo.entities.Oferta;
import com.turismo.entities.OfertaTipo;
import com.turismo.entities.OfertaTipoELIMINAR;

@Stateless
@LocalBean
public class OfertaDAO {
	@PersistenceContext(unitName = "MyPU")
	private EntityManager entityManager;

	public Oferta nuevaOfertaHotelera(String nombre, int cupo, LocalDateTime fecha_desde, LocalDateTime fecha_hasta,
			float precio, String tipo_habitacion, String politicas, String servicios, Destino destino,
			String foto_paquete, MedioPago medioPago, int cant_personas, Establecimiento establecimiento,
			OfertaTipo ofertaTipo) {
		Oferta oferta = new Oferta();
		oferta.setNombre(nombre);
		oferta.setCupo(cupo);
		oferta.setFecha_desde(fecha_desde);
		oferta.setFecha_hasta(fecha_hasta);
		oferta.setPrecio(precio);
		oferta.setTipo_habitacion(tipo_habitacion);
		oferta.setPoliticas(politicas);
		oferta.setServicios(servicios);
		oferta.setDestino(destino);
		oferta.setFoto_paquete(foto_paquete);
		oferta.setMedioPago(medioPago);
		oferta.setCant_personas(cant_personas);
		oferta.setEstablecimiento(establecimiento);
		oferta.setAgencia(null);
		oferta.setOfertaTipo(ofertaTipo);
		oferta.setDestino(destino);
		entityManager.persist(oferta);
		return oferta;
	}

	public Oferta nuevaOfertaPaquete(String nombre, int cupo, LocalDateTime fecha_desde, LocalDateTime fecha_hasta,
			float precio, String politicas, String servicios, Destino destino, String foto_paquete,
			String descripcionPaquete, MedioPago medioPago, int cant_personas, Agencia agencia, OfertaTipo ofertaTipo) {
		Oferta oferta = new Oferta();
		oferta.setNombre(nombre);
		oferta.setCupo(cupo);
		oferta.setFecha_desde(fecha_desde);
		oferta.setFecha_hasta(fecha_hasta);
		oferta.setPrecio(precio);
		oferta.setTipo_habitacion(null);
		oferta.setPoliticas(politicas);
		oferta.setServicios(servicios);
		oferta.setDestino(destino);
		oferta.setFoto_paquete(foto_paquete);
		oferta.setMedioPago(medioPago);
		oferta.setCant_personas(cant_personas);
		oferta.setEstablecimiento(null);
		oferta.setAgencia(agencia);
		oferta.setOfertaTipo(ofertaTipo);
		oferta.setDestino(destino);
		oferta.setDescriptionPaquete(descripcionPaquete);
		entityManager.persist(oferta);
		return oferta;
	}

	public void actualizarOferta(int oferta_id, String nombre, int cupo, LocalDateTime fecha_desde,
			LocalDateTime fecha_hasta, float precio, String tipo_habitacion, String politicas, String servicios,
			Destino destino, String foto_paquete, MedioPago medioPago, int cant_personas,
			Establecimiento establecimiento, Agencia agencia, OfertaTipo ofertaTipo) {
		Oferta oferta = buscarPorIdOferta(oferta_id);
		oferta.setNombre(nombre);
		oferta.setCupo(cupo);
		oferta.setFecha_desde(fecha_desde);
		oferta.setFecha_hasta(fecha_hasta);
		oferta.setPrecio(precio);
		oferta.setTipo_habitacion(tipo_habitacion);
		oferta.setPoliticas(politicas);
		oferta.setServicios(servicios);
		oferta.setDestino(destino);
		oferta.setFoto_paquete(foto_paquete);
		oferta.setMedioPago(medioPago);
		oferta.setCant_personas(cant_personas);
		oferta.setEstablecimiento(establecimiento);
		oferta.setAgencia(agencia);
		oferta.setOfertaTipo(ofertaTipo);
		entityManager.merge(oferta);

	}

	public Oferta buscarPorIdOferta(int idOferta) {
		try {
			return entityManager.find(Oferta.class, idOferta);
		} catch (Exception nre) {
			return null;
		}
	}

	// OJO CAMBIAR EL OR POR AND y ver porque no encuentra nada
	@SuppressWarnings("unchecked")
	public List<Oferta> buscarOfertasHotelera(int destinoId, int cantPersonas, LocalDateTime fDesde,
			LocalDateTime fHasta) {
		try {
			// Despues comparamos bien el tema de las fechas, por el momento lo dejo asi
			// para probar
			Query ofertasHotelerasQuery = entityManager.createQuery("SELECT o FROM Oferta o "
					+ " INNER JOIN o.destino d" + " INNER JOIN o.ofertaTipo ot " + "WHERE d.destino_id = :destinoId"
					+ " OR o.cant_personas = :cantPersonas" + " OR o.fecha_desde <= :fDesde"
					+ " OR o.fecha_hasta >= :fHasta" + " OR ot.nombre = :tipoDeOferta");
			ofertasHotelerasQuery.setParameter("destinoId", destinoId);
			ofertasHotelerasQuery.setParameter("cantPersonas", cantPersonas);
			ofertasHotelerasQuery.setParameter("fDesde", fDesde);
			ofertasHotelerasQuery.setParameter("fHasta", fHasta);
			ofertasHotelerasQuery.setParameter("tipoDeOferta", OfertaTipo.OFERTA_HOTELERA);
			return ofertasHotelerasQuery.getResultList();
		} catch (RollbackException exp) {
			return null;
		}
	}

	// OJO CAMBIAR EL OR POR AND y ver porque no encuentra nada
	@SuppressWarnings("unchecked")
	public List<Oferta> buscarOfertasPaquete(int destinoId, int cantPersonas, LocalDateTime fDesde,
			LocalDateTime fHasta) {
		try {
			Query ofertasHotelerasQuery = entityManager.createQuery("SELECT o FROM Oferta o "
					+ " INNER JOIN o.destino d" + " INNER JOIN o.ofertaTipo ot " + "WHERE d.destino_id = :destinoId"
					+ " OR o.cant_personas = :cantPersonas" + " OR o.fecha_desde <= :fDesde"
					+ " OR o.fecha_hasta >= :fHasta" + " OR ot.nombre = :tipoDeOferta");
			ofertasHotelerasQuery.setParameter("destinoId", destinoId);
			ofertasHotelerasQuery.setParameter("cantPersonas", cantPersonas);
			ofertasHotelerasQuery.setParameter("fDesde", fDesde);
			ofertasHotelerasQuery.setParameter("fHasta", fHasta);
			ofertasHotelerasQuery.setParameter("tipoDeOferta", OfertaTipo.OFERTA_PAQUETE);
			return ofertasHotelerasQuery.getResultList();
		} catch (RollbackException exp) {
			return null;
		}
	}
}
