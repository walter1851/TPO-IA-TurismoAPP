package com.turismo.dao;

import java.util.List;

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

	@SuppressWarnings("unchecked")
	public List<Imagen> buscarImagenesEstablecimiento(int establecimiento_id) {
		Query imagenesQuery = entityManager.createQuery("SELECT i FROM Imagen i INNER JOIN i.establecimiento e "
				+ "WHERE e.establecimiento_id = :establecimiento_id");
		imagenesQuery.setParameter("establecimiento_id", establecimiento_id);
		return imagenesQuery.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Imagen> buscarImagenesHotel(int hotel_id) {
		Query imagenesQuery = entityManager
				.createQuery("SELECT i FROM Imagen i INNER JOIN i.hotel h " + "WHERE h.hotel_id = :hotel_id");
		imagenesQuery.setParameter("hotel_id", hotel_id);
		return imagenesQuery.getResultList();
	}

	public boolean existeImagenEstablecimiento(int establecimiento_id, String imagenBase64) {
		try {
			Query imagenQuery = entityManager.createQuery("SELECT i FROM Imagen i INNER JOIN i.establecimiento e "
					+ "WHERE e.establecimiento_id = :establecimiento_id " + " AND i.imagenBase64 = :imagenBase64 ");
			imagenQuery.setParameter("establecimiento_id", establecimiento_id);
			imagenQuery.setParameter("imagenBase64", imagenBase64);
			Imagen imagen = (Imagen) imagenQuery.getSingleResult();
			if (imagen != null)
				return true;
			else
				return false;
		} catch (NoResultException nre) {
			return false;
		}
	}

	public boolean existeImagenHotel(int hotel_id, String imagenBase64) {
		try {
			Query imagenQuery = entityManager.createQuery("SELECT i FROM Imagen i INNER JOIN i.hotel h "
					+ "WHERE h.hotel_id = :hotel_id" + " AND i.imagenBase64 = :imagenBase64 ");
			imagenQuery.setParameter("hotel_id", hotel_id);
			imagenQuery.setParameter("imagenBase64", imagenBase64);
			Imagen imagen = (Imagen) imagenQuery.getSingleResult();
			if (imagen != null)
				return true;
			else
				return false;
		} catch (NoResultException nre) {
			return false;
		}
	}
}
