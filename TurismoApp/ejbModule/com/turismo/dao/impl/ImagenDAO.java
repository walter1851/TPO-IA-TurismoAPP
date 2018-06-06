package com.turismo.dao.impl;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.turismo.dao.ImagenDAOInterfaceLocal;
import com.turismo.entities.Establecimiento;
import com.turismo.entities.Hotel;
import com.turismo.entities.Imagen;

@Stateless
@LocalBean
public class ImagenDAO extends EntityManagerProvider implements ImagenDAOInterfaceLocal{
	public Imagen buscarPorCodigo(int codigo) {
		return 	getEntityManager().find(Imagen.class, codigo);
	}
	public void nuevaImagen(String url, Establecimiento establecimiento, Hotel hotel) {
		Imagen imagen= new Imagen();
		imagen.setUrl(url);
		imagen.setEstablecimiento(establecimiento);
		imagen.setHotel(hotel);
		getEntityManager().merge(imagen);
	}
	public void actualizarImagen(int imagen_id,String url,Establecimiento establecimiento, Hotel hotel) {
		Imagen imagen= buscarPorCodigo(imagen_id);
		imagen.setUrl(url);
		imagen.setEstablecimiento(establecimiento);
		imagen.setHotel(hotel);
		getEntityManager().merge(imagen);
	}
}
