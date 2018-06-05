package com.reservas.entities.dao;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.reservas.entities.Hotel;

@Stateless
@LocalBean
public class HotelDAO implements HotelDAOInterfaceLocal {
	@PersistenceContext(unitName = "MyPU")
	private EntityManager manager;
	
	public void nuevoHotel(Hotel hotel) {
		// se persiste el objeto hotel y todo el grafo
		manager.persist(hotel);
	}
	public void actualizarHotel(Hotel hotel) {
		manager.merge(hotel);
	}
	public Hotel buscarPorCodigo(int codigo) {
		return manager.find(Hotel.class, codigo);
	}
}
