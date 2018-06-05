package com.reservas.dao;

import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reservas.entities.Oferta;

@Stateless
@LocalBean
public class OfertaDAO implements OfertaDAOInterfaceLocal {
	@PersistenceContext(unitName = "MyPU")
	private EntityManager manager;

	public void nuevaOferta(Oferta oferta) {
		// se persiste el objeto oferta y todo el grafo
		manager.persist(oferta);
	}
	public void actualizarOferta(Oferta Oferta) {
		manager.merge(Oferta);
	}
	public Oferta buscarPorCodigo(int codigo) {
		return manager.find(Oferta.class, codigo);
	}
	public Oferta buscarPorVarios(String destino,int cantPersonas,Date dDesde,Date dHasta) {
		//return manager.find(Oferta.class, destino,cantPersonas,dHasta,dHasta);
		return null;
	}
}
