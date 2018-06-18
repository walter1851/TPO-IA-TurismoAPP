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

	public Destino nuevoDestino(String nombreDestino) {
		Destino destino = new Destino();
		destino.setNombre(nombreDestino);
		entityManager.persist(destino);
		return destino;
	}
	public Destino buscarPorIdDestino(int destinoId) {
		try {
			return entityManager.find(Destino.class, destinoId);
		} catch (Exception nre) {
			return null;
		}
	}
	public Destino buscarDestinoPorCodigo(int codigoDestino) {
		try {
			Query destinoQuery = entityManager.createQuery("SELECT d FROM destino d " + "WHERE d.codigo_destino = :codigo_destino");
			destinoQuery.setParameter("codigo_destino", codigoDestino);
			return (Destino) destinoQuery.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
	public Destino buscarDestinoPorNombre(String nombreDestino) {
		try {
			Query destinoQuery = entityManager.createQuery("SELECT d FROM destino d " + "WHERE d.nombre = :nombre");
			destinoQuery.setParameter("nombre", nombreDestino);
			return (Destino) destinoQuery.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
}
