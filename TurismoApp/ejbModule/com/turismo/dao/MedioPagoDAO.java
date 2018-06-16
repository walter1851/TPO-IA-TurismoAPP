package com.turismo.dao;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.turismo.entities.Destino;
import com.turismo.entities.MedioPago;
@Stateless
@LocalBean
public class MedioPagoDAO{
	@PersistenceContext(unitName = "MyPU")
	private EntityManager entityManager;
	public MedioPago nuevoMedioPago(String nombre, String codigo) {
		MedioPago medioPago= new MedioPago();
		medioPago.setNombre(nombre);
		medioPago.setCodigo(codigo);
		entityManager.persist(medioPago);
		return medioPago;
	}
	public void actualizarMedioPago(int medio_de_pago_id, String nombre, String codigo) {
		MedioPago medioPago= buscarPorIdMedioPago(medio_de_pago_id);
		medioPago.setNombre(nombre);
		medioPago.setCodigo(codigo);
		entityManager.merge(medioPago);
		
	}
	public MedioPago buscarPorIdMedioPago(int medio_de_pago_id) {
		try {
		return entityManager.find(MedioPago.class, medio_de_pago_id);
		} catch (NoResultException nre) {
			return null;
		}
	}
	public MedioPago buscarPorCodigoMedioPago(String codigoMedioPago) {
		try {
			Query medioPagoQuery = entityManager.createQuery("SELECT mp FROM MedioPago mp " + "WHERE mp.codigo = :codigoMedioPago ");
			medioPagoQuery.setParameter("codigoMedioPago", codigoMedioPago);
			return (MedioPago) medioPagoQuery.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
}
