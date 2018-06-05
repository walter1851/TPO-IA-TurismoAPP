package com.reservas.dao;

import javax.ejb.Local;

import com.reservas.entities.Hotel;

@Local
public interface HotelDAOInterfaceLocal {
	public void nuevoHotel(String nombre,String codigo_hotel);
	public void actualizarHotel(int hotel_id,String nombre, String codigo_hotel);
	public Hotel buscarPorCodigo(int codigo);
}
