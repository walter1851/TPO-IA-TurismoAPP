package com.reservas.dao.impl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reservas.dao.OfertaBloqueDAOInterfaceLocal;
import com.reservas.entities.OfertaBloque;


@Stateless
@LocalBean
public class OfertaBloqueDAO extends EntityManagerProvider implements OfertaBloqueDAOInterfaceLocal{
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
