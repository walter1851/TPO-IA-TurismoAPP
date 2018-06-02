package com.reservas.entities.dao;

import javax.ejb.Local;

import com.reservas.entities.Reserva;

@Local
public interface ReservaDAOLocal {
	public void nuevaReserva(Reserva reserva);
	public void actualizarReserva(Reserva reserva);
	public Reserva buscarPorCodigo(int codigo);
}
