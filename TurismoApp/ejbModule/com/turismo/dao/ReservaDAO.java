package com.turismo.dao;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.turismo.entities.MedioPago;
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
	public void crearReserva(Oferta oferta, int usuario_id, MedioPago medioPago, String nombre, String email,
			String dni) {
		Reserva reserva=new Reserva();
		reserva.setOferta(oferta);
		reserva.setUsuario_id(usuario_id);
		reserva.setMedioPago(medioPago);
		reserva.setNombre(nombre);
		reserva.setEmail(email);
		reserva.setDni(dni);
		entityManager.persist(reserva);
	}
	public void actualizarReserva(int reserva_id, int oferta_id, int usuario_id, int medio_de_pago_id, String nombre,
			String email, String dni) {
		Oferta oferta=ofertaDAO.buscarPorIdOferta(oferta_id);
		Reserva reserva=buscarPorIdReserva(reserva_id);
		reserva.setOferta(oferta);
		reserva.setUsuario_id(usuario_id);
		reserva.setMedioPago(medioPagoDAO.buscarPorIdMedioPago(medio_de_pago_id));
		reserva.setNombre(nombre);
		reserva.setEmail(email);
		entityManager.merge(reserva);
	}
	public Reserva buscarPorIdReserva(int reserva_id) {
		try {
		return entityManager.find(Reserva.class, reserva_id);
		} catch (NoResultException nre) {
			return null;
		}
	}	
}
