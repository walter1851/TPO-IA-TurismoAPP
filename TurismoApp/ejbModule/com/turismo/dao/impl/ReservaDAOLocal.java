package com.turismo.dao.impl;

import javax.ejb.Local;

import com.turismo.entities.Reserva;

@Local
public interface ReservaDAOLocal {
	public void nuevaReserva(int oferta_id,int usuario_id,int medio_de_pago_id,String nombre, String email,String dni);
	public void actualizarReserva(int reserva_id,int oferta_id,int usuario_id,int medio_de_pago_id,String nombre, String email,String dni);
	public Reserva buscarPorIdReserva(int reserva_id);
}
