package com.turismo.dao;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.turismo.entities.MedioPago;
@Stateless
@LocalBean
public class MedioPagoDAO implements MedioPagoDAOLocal {
	@PersistenceContext(unitName = "MyPU")
	private EntityManager entityManager;
	public void nuevoMedioPago(String nombre, String codigo) {
		MedioPago medioPago= new MedioPago();
		medioPago.setNombre(nombre);
		medioPago.setCodigo(codigo);
		entityManager.merge(medioPago);
	}
	public void actualizarBloque(int medio_de_pago_id, String nombre, String codigo) {
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
}
