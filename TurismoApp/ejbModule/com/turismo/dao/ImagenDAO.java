package com.turismo.dao;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.turismo.entities.Establecimiento;
import com.turismo.entities.Hotel;
import com.turismo.entities.Imagen;

@Stateless
@LocalBean
public class ImagenDAO implements ImagenDAOLocal{
	@PersistenceContext(unitName = "MyPU")
	private EntityManager entityManager;
	
	public Imagen buscarPorIdImagen(int imagenId) {
		try {
		return entityManager.find(Imagen.class, imagenId);
		} catch (NoResultException nre) {
			return null;
		}
	}
	public void nuevaImagen(String url, Establecimiento establecimiento, Hotel hotel) {
		Imagen imagen= new Imagen();
		imagen.setUrl(url);
		imagen.setEstablecimiento(establecimiento);
		imagen.setHotel(hotel);
		entityManager.merge(imagen);
	}
	public void actualizarImagen(int imagen_id,String url,Establecimiento establecimiento, Hotel hotel) {
		Imagen imagen= buscarPorIdImagen(imagen_id);
		imagen.setUrl(url);
		imagen.setEstablecimiento(establecimiento);
		imagen.setHotel(hotel);
		entityManager.merge(imagen);
	}
}
