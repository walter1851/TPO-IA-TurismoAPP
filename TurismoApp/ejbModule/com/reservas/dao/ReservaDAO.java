package com.reservas.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reservas.entities.Reserva;


@Stateless
@LocalBean
public class ReservaDAO implements ReservaDAOInterfaceLocal{
	@PersistenceContext(unitName = "MyPU")
	private EntityManager manager;
	
	public void nuevaReserva(Reserva reserva) {
		// se persiste el objeto reserva y todo el grafo
		manager.persist(reserva);
	}
	public void actualizarReserva(Reserva reserva) {
		manager.merge(reserva);
	}
	public Reserva buscarPorCodigo(int codigo) {
		return manager.find(Reserva.class, codigo);
	}
}
