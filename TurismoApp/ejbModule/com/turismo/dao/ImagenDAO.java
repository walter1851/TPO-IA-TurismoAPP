package com.turismo.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import com.turismo.entities.Establecimiento;
import com.turismo.entities.Hotel;
import com.turismo.entities.Imagen;

@Stateless
@LocalBean
public class ImagenDAO {
	@PersistenceContext(unitName = "MyPU")
	private EntityManager entityManager;

	public Imagen buscarPorIdImagen(int imagenId) {
		try {
			return entityManager.find(Imagen.class, imagenId);
		} catch (NoResultException nre) {
			return null;
		}
	}
	public Imagen nuevaImagen(String imagenBase64, Establecimiento establecimiento, Hotel hotel) {
		try {
			Imagen imagen = new Imagen();
			imagen.setImagenBase64(imagenBase64);
			imagen.setEstablecimiento(establecimiento);
			imagen.setHotel(hotel);
			entityManager.persist(imagen);
			return imagen;
		} catch (PersistenceException pe) {
			return null;
		}
	}
	public boolean actualizarImagen(int imagen_id, String imagenBase64, Establecimiento establecimiento, Hotel hotel) {
		try {
			Imagen imagen = buscarPorIdImagen(imagen_id);
			imagen.setImagenBase64(imagenBase64);
			imagen.setEstablecimiento(establecimiento);
			imagen.setHotel(hotel);
			entityManager.merge(imagen);
			return true;
		} catch (PersistenceException pe) {
			return false;
		}
	}

	public Imagen buscarImagenPorBase64(int imagen_id,String imagenBase64) {
		try {
			Query imagenQuery = entityManager
					.createQuery("SELECT i FROM Imagen i " + "WHERE i.imagenBase64 = :imagenBase64 "+
			" AND i.imagen_id = :imagen_id");
			imagenQuery.setParameter("imagenBase64", imagenBase64);
			imagenQuery.setParameter("imagen_id", imagen_id);
			return (Imagen) imagenQuery.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
	public Imagen buscarImagenPorIdEstablecimiento(int establecimiento_id) {
		try {
			Query imagenQuery = entityManager
					.createQuery("SELECT i FROM Imagen i INNER JOIN i.Establecimiento e " + "WHERE e.establecimiento_id = :establecimiento_id ");
			imagenQuery.setParameter("establecimiento_id", establecimiento_id);
			return (Imagen) imagenQuery.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
	public Imagen buscarImagenPorIdHotel(int hotel_id) {
		try {
			Query imagenQuery = entityManager
					.createQuery("SELECT i FROM Imagen i INNER JOIN i.Hotel h " + "WHERE h.hotel_id = :hotel_id ");
			imagenQuery.setParameter("hotel_id", hotel_id);
			return (Imagen) imagenQuery.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
}
