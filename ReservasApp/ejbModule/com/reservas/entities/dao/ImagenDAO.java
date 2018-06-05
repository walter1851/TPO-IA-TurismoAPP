package com.reservas.entities.dao;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.reservas.entities.Hotel;
import com.reservas.entities.Imagen;

@Stateless
@LocalBean
public class ImagenDAO implements ImagenDAOInterfaceLocal{
	@PersistenceContext(unitName = "MyPU")
	private EntityManager manager;
	
	public void nuevaImagen(Imagen imagen) {
		// se persiste el objeto imagen y todo el grafo
		manager.persist(imagen);
	}
	public void actualizarImagen(Hotel hotel) {
		manager.merge(hotel);
	}
	public Imagen buscarPorCodigo(int codigo) {
		return manager.find(Imagen.class, codigo);
	}
}
