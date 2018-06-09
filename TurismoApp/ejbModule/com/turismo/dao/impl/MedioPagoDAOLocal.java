package com.turismo.dao.impl;


import javax.ejb.Local;

import com.turismo.entities.MedioPago;
@Local
public interface MedioPagoDAOLocal {
	public void nuevoMedioPago(String nombre,String codigo);
	public void actualizarBloque(int medio_de_pago_id,String nombre,String codigo);
	public MedioPago buscarPorId(int medio_de_pago_id);
}
