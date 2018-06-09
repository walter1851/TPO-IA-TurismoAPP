package com.turismo.dao.impl;

import javax.ejb.Local;

import com.turismo.entities.Hotel;

@Local
public interface HotelDAOLocal {
	public void nuevoHotel(String nombre,String codigo_hotel);
	public void actualizarHotel(int hotel_id,String nombre, String codigo_hotel);
	public Hotel buscarPorCodigo(int codigo);
}
