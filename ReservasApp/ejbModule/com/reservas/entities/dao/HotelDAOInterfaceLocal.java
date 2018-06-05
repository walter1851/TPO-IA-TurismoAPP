package com.reservas.entities.dao;

import javax.ejb.Local;

import com.reservas.entities.Hotel;

@Local
public interface HotelDAOInterfaceLocal {
	public void nuevoHotel(Hotel hotel);
	public void actualizarHotel(Hotel hotel);
	public Hotel buscarPorCodigo(int codigo);
}
