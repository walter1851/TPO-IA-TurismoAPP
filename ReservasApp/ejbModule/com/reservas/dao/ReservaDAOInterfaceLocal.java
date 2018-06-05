package com.reservas.dao;

import javax.ejb.Local;

import com.reservas.entities.Reserva;

@Local
public interface ReservaDAOInterfaceLocal {
	public void nuevaReserva(Reserva reserva);
	public void actualizarReserva(Reserva reserva);
	public Reserva buscarPorCodigo(int codigo);
}
