package com.reservas.dao.impl;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.reservas.dao.MedioPagoDAOInterfaceLocal;
import com.reservas.entities.MedioPago;
@Stateless
@LocalBean
public class MedioPagoDAO extends EntityManagerProvider implements MedioPagoDAOInterfaceLocal {
	public void nuevoMedioPago(String nombre, String codigo) {
		MedioPago medioPago= new MedioPago();
		medioPago.setNombre(nombre);
		medioPago.setCodigo(codigo);
		getEntityManager().merge(medioPago);
	}
	public void actualizarBloque(int medio_de_pago_id, String nombre, String codigo) {
		MedioPago medioPago= buscarPorId(medio_de_pago_id);
		medioPago.setNombre(nombre);
		medioPago.setCodigo(codigo);
		getEntityManager().merge(medioPago);
		
	}
	public MedioPago buscarPorId(int medio_de_pago_id) {
		return getEntityManager().find(MedioPago.class, medio_de_pago_id);
	}
}
