package com.turismo.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.turismo.entities.Agencia;
import com.turismo.entities.Oferta;
import com.turismo.entities.OfertaBloque;

@Stateless
@LocalBean
public class OfertaBloqueDAO implements OfertaBloqueDAOLocal {
	@PersistenceContext(unitName = "MyPU")
	private EntityManager entityManager;

	public void nuevoBloque(Oferta oferta, Date fecha, int cupo) {
		OfertaBloque ofertaBloque = new OfertaBloque();
		ofertaBloque.setOferta(oferta);
		ofertaBloque.setFecha_Bloque(fecha);
		ofertaBloque.setCupo(cupo);
		entityManager.persist(ofertaBloque);
	}
	public void actualizarBloque(OfertaBloque ofertaBloque) {
		entityManager.merge(ofertaBloque);
	}

	public OfertaBloque buscarPorIdBloque(int oferta_bloque_id) {
		try {
			return entityManager.find(OfertaBloque.class, oferta_bloque_id);
		} catch (NoResultException nre) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<OfertaBloque> buscarBloques(int ofertaId,String fDesde, String fHasta, int cantPersonas) {
		//Despues comparamos bien el tema de las fechas, por el momento lo dejo asi para probar
		Query bloqueQuery = entityManager
				.createQuery("SELECT ob FROM OfertaBloque ob INNER JOIN ob.oferta o" + "WHERE o.fecha_desde >= :fDesde "
						+ "AND o.fecha_hasta >= :fHasta " + "AND o.cant_personas >= :cantPersonas "
						+ "AND o.oferta_id = :ofertaId ");
		bloqueQuery.setParameter("fDesde", fDesde);
		bloqueQuery.setParameter("fHasta", fHasta);
		bloqueQuery.setParameter("cantPersonas", cantPersonas);
		bloqueQuery.setParameter("ofertaId", ofertaId);
		return bloqueQuery.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<OfertaBloque> buscarBloques(int ofertaId,String fDesde, String fHasta, int cantPersonas,String tipoHabitacion) {
		//Despues comparamos bien el tema de las fechas, por el momento lo dejo asi para probar
		Query bloqueQuery = entityManager
				.createQuery("SELECT ob FROM OfertaBloque ob INNER JOIN ob.oferta o" + "WHERE o.fecha_desde >= :fDesde "
						+ "AND o.fecha_hasta >= :fHasta " + "AND o.cant_personas >= :cantPersonas "
						+ "AND o.oferta_id = :ofertaId " + "o.tipo_habitacion=:tipoHabitacion");
		bloqueQuery.setParameter("fDesde", fDesde);
		bloqueQuery.setParameter("fHasta", fHasta);
		bloqueQuery.setParameter("cantPersonas", cantPersonas);
		bloqueQuery.setParameter("ofertaId", ofertaId);
		bloqueQuery.setParameter("tipoHabitacion", tipoHabitacion);
		return bloqueQuery.getResultList();
	}
}
