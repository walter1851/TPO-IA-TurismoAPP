package com.reservas.entities.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.reservas.entities.Establecimiento;

@Stateless
@LocalBean
public class EstablecimientoDAO implements EstablecimientoDAOLocal{
	@PersistenceContext(unitName = "MyPU")
	private EntityManager manager;
	
	public void nuevoEstablecimiento(Establecimiento establecimiento) {
		// se persiste el objeto establecimiento y todo el grafo
		manager.persist(establecimiento);
	}
	public void actualizarEstablecimiento(Establecimiento establecimiento) {
		manager.merge(establecimiento);
	}
	public Establecimiento buscarPorCodigo(int codigo) {
		return manager.find(Establecimiento.class, codigo);
	}
}
