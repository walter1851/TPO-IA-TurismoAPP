package com.reservas.dao.impl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reservas.dao.ReservaDAOInterfaceLocal;
import com.reservas.entities.Reserva;


@Stateless
@LocalBean
public class ReservaDAO extends EntityManagerProvider implements ReservaDAOInterfaceLocal{
	
	public void nuevaReserva(Reserva reserva) {
		// se persiste el objeto reserva y todo el grafo
		getEntityManager().persist(reserva);
	}
	public void actualizarReserva(Reserva reserva) {
		getEntityManager().merge(reserva);
	}
	public Reserva buscarPorCodigo(int codigo) {
		return getEntityManager().find(Reserva.class, codigo);
	}
}
