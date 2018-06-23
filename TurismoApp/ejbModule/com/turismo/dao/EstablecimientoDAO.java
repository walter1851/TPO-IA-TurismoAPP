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

@Stateless
@LocalBean
public class EstablecimientoDAO {
	@PersistenceContext(unitName = "MyPU")
	private EntityManager entityManager;

	public Establecimiento nuevoEstablecimiento(String nombre, String direccion, String ciudad, String descripcion,
			int estrellas, String latitud, String longitud, String codigo_establecimiento, Hotel hotel) {
		try {
			Establecimiento establecimiento = new Establecimiento();
			establecimiento.setNombre(nombre);
			establecimiento.setDireccion(direccion);
			establecimiento.setCiudad(ciudad);
			establecimiento.setDescripcion(descripcion);
			establecimiento.setEstrellas(estrellas);
			establecimiento.setLatitud(latitud);
			establecimiento.setLongitud(longitud);
			establecimiento.setCodigo_establecimiento(codigo_establecimiento);
			establecimiento.setHotel(hotel);
			entityManager.persist(establecimiento);
			return establecimiento;
		} catch (PersistenceException pe) {
			return null;
		}
	}
	public void actualizarEstablecimiento(Establecimiento establecimiento) {
		entityManager.merge(establecimiento);
	}

	public Establecimiento buscarPorIdEstablecimiento(int establecimientoId) {
		try {
			return entityManager.find(Establecimiento.class, establecimientoId);
		} catch (NoResultException nre) {
			return null;
		}
	}

	public Establecimiento buscarPorCodigoEstablecimiento(String codigo_establecimiento) {
		try {
			Query establecimientoQuery = entityManager.createQuery(
					"SELECT e FROM Establecimiento e " + "WHERE codigo_establecimiento = :codigo_establecimiento ");
			establecimientoQuery.setParameter("codigo_establecimiento", codigo_establecimiento);
			return (Establecimiento) establecimientoQuery.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
}
