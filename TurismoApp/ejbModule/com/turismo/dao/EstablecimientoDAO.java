package com.turismo.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.turismo.entities.Establecimiento;
import com.turismo.entities.Estado;
import com.turismo.entities.Hotel;

@Stateless
@LocalBean
public class EstablecimientoDAO{
	@PersistenceContext(unitName = "MyPU")
	private EntityManager entityManager;

	public Establecimiento nuevoEstablecimiento(String nombre, String direccion, String ciudad, Estado estado, String descripcion,
			int estrellas, String latitud,String longitud, int codigo_establecimiento, Hotel hotel) {
		Establecimiento establecimiento = new Establecimiento();
		establecimiento.setNombre(nombre);
		establecimiento.setDireccion(direccion);
		establecimiento.setCiudad(ciudad);
		establecimiento.setEstado(estado);
		establecimiento.setDescripcion(descripcion);
		establecimiento.setEstrellas(estrellas);
		establecimiento.setLatitud(latitud);
		establecimiento.setLongitud(longitud);
		establecimiento.setCodigo_establecimiento(codigo_establecimiento);
		establecimiento.setHotel(hotel);
		entityManager.persist(establecimiento);
		return establecimiento;
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

	public Establecimiento buscarPorCodigoEstablecimiento(int codigo_establecimiento) {
		try {
			Query establecimientoQuery = entityManager.createQuery(
					"SELECT e FROM establecimiento e " + "WHERE codigo_establecimiento = :codigo_establecimiento ");
			establecimientoQuery.setParameter("codigo_establecimiento", codigo_establecimiento);
			return (Establecimiento) establecimientoQuery.getSingleResult();
		} catch (Exception nre) {
			return null;
		}
	}
}
