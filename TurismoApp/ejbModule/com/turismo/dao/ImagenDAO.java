package com.turismo.dao;


import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.turismo.entities.Establecimiento;
import com.turismo.entities.Hotel;
import com.turismo.entities.Imagen;

@Stateless
@LocalBean
public class ImagenDAO{
	@PersistenceContext(unitName = "MyPU")
	private EntityManager entityManager;
	
	public Imagen buscarPorIdImagen(int imagenId) {
		try {
		return entityManager.find(Imagen.class, imagenId);
		} catch (NoResultException nre) {
			return null;
		}
	}
	public Imagen nuevaImagen(String url, Establecimiento establecimiento, Hotel hotel) {
		Imagen imagen= new Imagen();
		imagen.setImagenBase64(url);
		imagen.setEstablecimiento(establecimiento);
		imagen.setHotel(hotel);
		entityManager.persist(imagen);
		return imagen;
	}
	public void actualizarImagen(int imagen_id,String url,Establecimiento establecimiento, Hotel hotel) {
		Imagen imagen= buscarPorIdImagen(imagen_id);
		imagen.setImagenBase64(url);
		imagen.setEstablecimiento(establecimiento);
		imagen.setHotel(hotel);
		entityManager.merge(imagen);
	}
	public Imagen buscarImagenPorURL(String url) {
		try {
			Query imagenQuery = entityManager.createQuery("SELECT i FROM Imagen i " + "WHERE url = :url ");
			imagenQuery.setParameter("url", url);
			return (Imagen) imagenQuery.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
}
