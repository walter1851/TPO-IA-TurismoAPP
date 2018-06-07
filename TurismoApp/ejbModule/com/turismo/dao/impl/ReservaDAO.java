package com.turismo.dao.impl;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.turismo.dao.ReservaDAOLocal;
import com.turismo.entities.Oferta;
import com.turismo.entities.Reserva;


@Stateless
@LocalBean
public class ReservaDAO implements ReservaDAOLocal{
	@PersistenceContext(unitName = "MyPU")
	private EntityManager entityManager;
	@EJB
	private OfertaDAO ofertaDAO;
	@EJB
	private MedioPagoDAO medioPagoDAO;
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
		return entityManager.find(Reserva.class, reserva_id);
	}	
}
