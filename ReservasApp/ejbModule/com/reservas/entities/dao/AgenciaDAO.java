package com.reservas.entities.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reservas.entities.Agencia;


@Stateless
@LocalBean
public class AgenciaDAO implements AgenciaDAOInterfaceLocal{
	@PersistenceContext(unitName = "MyPU")
	private EntityManager manager;
	
	public void nuevaAgencia(Agencia agencia) {
		// se persiste el objeto agencia y todo el grafo
		manager.persist(agencia);
	}
	public void actualizarAgencia(Agencia agencia) {
		manager.merge(agencia);
	}
	public Agencia buscarPorCodigo(int codigo) {
		return manager.find(Agencia.class, codigo);
	}
}
