package com.reservas.dao.impl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reservas.dao.ReservaDAOInterfaceLocal;
import com.reservas.entities.Oferta;
import com.reservas.entities.Reserva;


@Stateless
@LocalBean
public class ReservaDAO extends EntityManagerProvider implements ReservaDAOInterfaceLocal{
	public void nuevaReserva(Oferta oferta, int usuario_id, int medio_de_pago_id, String nombre, String email,
			String dni) {
		// TODO Auto-generated method stub
		
	}
	public void actualizarReserva(int reserva_id, Oferta oferta, int usuario_id, int medio_de_pago_id, String nombre,
			String email, String dni) {
		// TODO Auto-generated method stub
		
	}
	public Reserva buscarPorCodigo(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
