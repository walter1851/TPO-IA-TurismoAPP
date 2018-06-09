package com.turismo.dao;

import javax.ejb.Local;

import com.turismo.entities.MedioPago;
import com.turismo.entities.Oferta;
import com.turismo.entities.Reserva;

@Local
public interface ReservaDAOLocal {
	public void crearReserva(Oferta oferta, int usuario_id, MedioPago medioPago, String nombre, String email,
			String dni);
	public void actualizarReserva(int reserva_id,int oferta_id,int usuario_id,int medio_de_pago_id,String nombre, String email,String dni);
	public Reserva buscarPorIdReserva(int reserva_id);
}
