package com.turismo.dao.impl;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.turismo.entities.Establecimiento;
import com.turismo.entities.Hotel;
import com.turismo.entities.Imagen;

@Stateless
@LocalBean
public class ImagenDAO implements ImagenDAOLocal{
	@PersistenceContext(unitName = "MyPU")
	private EntityManager entityManager;
	
	public Imagen buscarPorCodigo(int codigo) {
		return entityManager.find(Imagen.class, codigo);
	}
	public void nuevaImagen(String url, Establecimiento establecimiento, Hotel hotel) {
		Imagen imagen= new Imagen();
		imagen.setUrl(url);
		imagen.setEstablecimiento(establecimiento);
		imagen.setHotel(hotel);
		entityManager.merge(imagen);
	}
	public void actualizarImagen(int imagen_id,String url,Establecimiento establecimiento, Hotel hotel) {
		Imagen imagen= buscarPorCodigo(imagen_id);
		imagen.setUrl(url);
		imagen.setEstablecimiento(establecimiento);
		imagen.setHotel(hotel);
		entityManager.merge(imagen);
	}
}
