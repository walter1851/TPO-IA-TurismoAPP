package com.turismo.dao;


import javax.ejb.Local;

import com.turismo.entities.MedioPago;
@Local
public interface MedioPagoDAOInterfaceLocal {
	public void nuevoMedioPago(String nombre,String codigo);
	public void actualizarBloque(int medio_de_pago_id,String nombre,String codigo);
	public MedioPago buscarPorId(int medio_de_pago_id);
}
