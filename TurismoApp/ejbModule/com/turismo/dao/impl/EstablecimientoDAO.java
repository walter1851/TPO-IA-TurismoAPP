package com.turismo.dao.impl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.turismo.dao.EstablecimientoDAOLocal;
import com.turismo.entities.Establecimiento;
import com.turismo.entities.Hotel;

@Stateless
@LocalBean
public class EstablecimientoDAO implements EstablecimientoDAOLocal {
	@PersistenceContext(unitName = "MyPU")
	private EntityManager entityManager;
	
	public void nuevoEstablecimiento(String nombre, String direccion, String ciudad, String estado, String descripcion,
			String estrellas, String mapa, String codigo_establecimiento, Hotel hotel) {
		Establecimiento establecimiento = new Establecimiento();
		establecimiento.setNombre(nombre);
		establecimiento.setDireccion(direccion);
		establecimiento.setCiudad(ciudad);
		establecimiento.setEstado(estado);
		establecimiento.setDescripcion(descripcion);
		establecimiento.setEstrellas(estrellas);
		establecimiento.setMapa(mapa);
		establecimiento.setCodigo_establecimiento(codigo_establecimiento);
		establecimiento.setHotel(hotel);
		entityManager.persist(establecimiento);
	}

	public void actualizarEstablecimiento(Establecimiento establecimiento) {
		entityManager.merge(establecimiento);
	}

	public Establecimiento buscarPorCodigoEstablecimiento(String codigo_establecimiento) {
		Query establecimientoQuery = entityManager.createQuery(
				"SELECT e FROM establecimientos e " + "WHERE e.codigo_establecimiento = :codigo_establecimiento ");
		establecimientoQuery.setParameter("codigo_establecimiento", "codigo_establecimiento");
		return (Establecimiento) establecimientoQuery.getSingleResult();
	}
}
