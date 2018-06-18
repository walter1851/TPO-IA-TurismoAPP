package com.turismo.dao;

import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.turismo.entities.Oferta;
import com.turismo.entities.OfertaBloque;

@Stateless
@LocalBean
public class OfertaBloqueDAO{
	@PersistenceContext(unitName = "MyPU")
	private EntityManager entityManager;

	public boolean nuevoBloque(Oferta oferta, LocalDateTime fecha, int cupo) {
		try {
		OfertaBloque ofertaBloque = new OfertaBloque();
		ofertaBloque.setOferta(oferta);
		ofertaBloque.setFecha_Bloque(fecha);
		ofertaBloque.setCupo(cupo);
		entityManager.persist(ofertaBloque);
		return true;
		} catch (Exception e) {
			return false;
		}
	}
	public boolean actualizarBloque(OfertaBloque ofertaBloque) {
		try {
			entityManager.merge(ofertaBloque);
		return true;
		} catch (Exception e) {
			return false;
		}
	}

	public OfertaBloque buscarPorIdBloque(int oferta_bloque_id) {
		try {
			return entityManager.find(OfertaBloque.class, oferta_bloque_id);
		} catch (NoResultException nre) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<OfertaBloque> buscarBloquesDePaquetes(int ofertaId,LocalDateTime fDesde, LocalDateTime fHasta, int cantPersonas) {
		Query bloqueQuery = entityManager
				.createQuery("SELECT ob FROM OfertaBloque ob INNER JOIN ob.oferta o " + " WHERE o.cant_personas >= :cantPersonas "
						+ "AND o.oferta_id = :ofertaId "+" AND o.fecha_desde <= :fDesde AND o.fecha_hasta >= :fHasta");
		bloqueQuery.setParameter("fDesde", fDesde);
		bloqueQuery.setParameter("fHasta", fHasta);
		bloqueQuery.setParameter("cantPersonas", cantPersonas);
		bloqueQuery.setParameter("ofertaId", ofertaId);
		return bloqueQuery.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<OfertaBloque> buscarBloquesDeHoteleria(int ofertaId,LocalDateTime fDesde, LocalDateTime fHasta,String tipoHabitacion) {
		Query bloqueQuery = entityManager
				.createQuery("SELECT ob FROM OfertaBloque ob INNER JOIN ob.oferta o " + "WHERE o.oferta_id = :ofertaId " + 
		"AND o.tipo_habitacion=:tipoHabitacion "+" AND o.fecha_desde <= :fDesde AND o.fecha_hasta >= :fHasta");
		bloqueQuery.setParameter("fDesde", fDesde);
		bloqueQuery.setParameter("fHasta", fHasta);
		bloqueQuery.setParameter("ofertaId", ofertaId);
		bloqueQuery.setParameter("tipoHabitacion", tipoHabitacion);
		return bloqueQuery.getResultList();
	}
}
