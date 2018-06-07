package com.turismo.dao.impl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.turismo.dao.HotelDAOLocal;
import com.turismo.entities.Hotel;

@Stateless
@LocalBean
public class HotelDAO implements HotelDAOLocal {
	@PersistenceContext(unitName = "MyPU")
	private EntityManager entityManager;
	
	public void nuevoHotel(String nombre, String codigo_hotel) {
		Hotel hotel = new Hotel();
		hotel.setNombre(nombre);
		hotel.setCodigo_hotel(codigo_hotel);
		entityManager.persist(hotel);
	}

	public void actualizarHotel(int hotel_id,String nombre,String codigo_hotel) {
		Hotel hotel=buscarPorCodigo(hotel_id);
				hotel.setNombre(nombre);
				hotel.setCodigo_hotel(codigo_hotel);
				entityManager.merge(hotel);
	}

	public Hotel buscarPorCodigo(int codigo) {
		return entityManager.find(Hotel.class, codigo);
	}
}
