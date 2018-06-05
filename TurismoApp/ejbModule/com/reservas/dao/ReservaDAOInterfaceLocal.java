package com.reservas.dao;

import javax.ejb.Local;

import com.reservas.entities.Oferta;
import com.reservas.entities.Reserva;

@Local
public interface ReservaDAOInterfaceLocal {
	public void nuevaReserva(int oferta_id,int usuario_id,int medio_de_pago_id,String nombre, String email,String dni);
	public void actualizarReserva(int reserva_id,int oferta_id,int usuario_id,int medio_de_pago_id,String nombre, String email,String dni);
	public Reserva buscarPorIdReserva(int reserva_id);
}
