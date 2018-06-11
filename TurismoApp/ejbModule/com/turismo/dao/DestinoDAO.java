package com.turismo.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.turismo.entities.Destino;

@Stateless
@LocalBean
public class DestinoDAO {
	@PersistenceContext(unitName = "MyPU")
	private EntityManager entityManager;

	public Destino buscarPorIdDestino(int destinoId) {
		try {
			return entityManager.find(Destino.class, destinoId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	public Destino buscarPorNombre(String nombreDestino) {
		try {
			Query destinoQuery = entityManager.createQuery("SELECT d FROM destino d " + "WHERE d.nombre = :nombre ");
			destinoQuery.setParameter("nombre", nombreDestino);
			return (Destino) destinoQuery.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
}
