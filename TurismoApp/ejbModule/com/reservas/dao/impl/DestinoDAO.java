package com.reservas.dao.impl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reservas.dao.DestinoDAOInterfaceLocal;
import com.reservas.entities.Destino;
@Stateless
@LocalBean
public class DestinoDAO extends EntityManagerProvider implements DestinoDAOInterfaceLocal{
	public Destino buscarPorCodigo(int codigo) {
		return getEntityManager().find(Destino.class, codigo);
	}
	public Destino buscarPorNombre(String nombreDestino) {
		return getEntityManager().find(Destino.class, nombreDestino);
	}
}
