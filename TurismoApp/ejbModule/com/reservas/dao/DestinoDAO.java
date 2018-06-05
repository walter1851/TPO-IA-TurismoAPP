package com.reservas.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.reservas.entities.Destino;
@Stateless
@LocalBean
public class DestinoDAO implements DestinoDAOInterfaceLocal{
	@PersistenceContext(unitName = "MyPU")
	private EntityManager manager;
	
	public Destino buscarPorCodigo(int codigo) {
		return manager.find(Destino.class, codigo);
	}
	public Destino buscarPorNombre(String nombreDestino) {
		return manager.find(Destino.class, nombreDestino);
	}
}
