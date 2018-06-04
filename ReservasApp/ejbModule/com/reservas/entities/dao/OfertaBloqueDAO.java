package com.reservas.entities.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reservas.entities.OfertaBloque;


@Stateless
@LocalBean
public class OfertaBloqueDAO implements OfertaBloqueDAOLocal{
	@PersistenceContext(unitName = "MyPU")
	private EntityManager manager;
	
	public void nuevoBloque(OfertaBloque ofertaBloque) {
		// se persiste el objeto hotel y todo el grafo
		manager.persist(ofertaBloque);
	}
	public void actualizarBloque(OfertaBloque ofertaBloque) {
		manager.merge(ofertaBloque);
	}
	public OfertaBloque buscarPorCodigo(int codigo) {
		return manager.find(OfertaBloque.class, codigo);
	}
}
