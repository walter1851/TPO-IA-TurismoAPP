package com.reservas.dao.impl;

import javax.ejb.EJB;
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
	@EJB
	OfertaDAO ofertaDAO;
	@EJB
	MedioPagoDAO medioPagoDAO;
	public void nuevaReserva(int oferta_id, int usuario_id, int medio_de_pago_id, String nombre, String email,
			String dni) {
		Oferta oferta=ofertaDAO.buscarPorCodigo(oferta_id);
		Reserva reserva=new Reserva();
		reserva.setOferta(oferta);
		reserva.setUsuario_id(usuario_id);
		reserva.setMedioPago(medioPagoDAO.buscarPorId(medio_de_pago_id));
		reserva.setNombre(nombre);
		reserva.setEmail(email);
	}
	public void actualizarReserva(int reserva_id, int oferta_id, int usuario_id, int medio_de_pago_id, String nombre,
			String email, String dni) {
		Oferta oferta=ofertaDAO.buscarPorCodigo(oferta_id);
		Reserva reserva=buscarPorIdReserva(reserva_id);
		reserva.setOferta(oferta);
		reserva.setUsuario_id(usuario_id);
		reserva.setMedioPago(medioPagoDAO.buscarPorId(medio_de_pago_id));
		reserva.setNombre(nombre);
		reserva.setEmail(email);
	}
	public Reserva buscarPorIdReserva(int reserva_id) {
		return getEntityManager().find(Reserva.class, reserva_id);
	}	
}
