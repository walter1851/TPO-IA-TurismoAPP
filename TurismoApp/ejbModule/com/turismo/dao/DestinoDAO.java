package com.turismo.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.turismo.entities.Destino;

@Stateless
@LocalBean
public class DestinoDAO implements DestinoDAOLocal {
	@PersistenceContext(unitName = "MyPU")
	private EntityManager entityManager;
	public Destino buscarPorCodigo(int codigo) {
		return entityManager.find(Destino.class, codigo);
	}

	public Destino buscarPorNombre(String nombreDestino) {
		Query destinoQuery = entityManager.createQuery("SELECT d FROM destinos d " + "WHERE d.nombre = :nombre ");
		destinoQuery.setParameter("nombre", "nombreDestino");
		return (Destino) destinoQuery.getSingleResult();
	}
}
