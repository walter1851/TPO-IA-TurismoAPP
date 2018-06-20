package com.turismo.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import com.turismo.entities.MedioPago;

@Stateless
@LocalBean
public class MedioPagoDAO {
	@PersistenceContext(unitName = "MyPU")
	private EntityManager entityManager;

	public MedioPago nuevoMedioPago(String nombre) {
		try {
			MedioPago medioPago = new MedioPago();
			medioPago.setNombre(nombre);
			entityManager.persist(medioPago);
			return medioPago;
		} catch (PersistenceException pe) {
			return null;
		}
	}

	public boolean actualizarMedioPago(int medio_de_pago_id, String nombre) {
		try {
			MedioPago medioPago = buscarPorIdMedioPago(medio_de_pago_id);
			medioPago.setNombre(nombre);
			entityManager.merge(medioPago);
			return true;
		} catch (PersistenceException pe) {
			return false;
		}
	}

	public MedioPago buscarPorIdMedioPago(int medio_de_pago_id) {
		try {
			return entityManager.find(MedioPago.class, medio_de_pago_id);
		} catch (NoResultException nre) {
			return null;
		}
	}
}
