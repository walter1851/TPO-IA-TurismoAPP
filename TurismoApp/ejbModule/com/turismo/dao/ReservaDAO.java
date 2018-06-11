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
public class ReservaDAO{
	@PersistenceContext(unitName = "MyPU")
	private EntityManager entityManager;
	@EJB
	private OfertaDAO ofertaDAO;
	@EJB
	private MedioPagoDAO medioPagoDAO;

	public boolean crearReserva(int oferta_id, int usuario_id, String medioPago, String nombre, String email,
			String dni) {
		try {
			Oferta oferta=ofertaDAO.buscarPorIdOferta(oferta_id);
			Reserva reserva = new Reserva();
			reserva.setOferta(oferta);
			reserva.setUsuario_id(usuario_id);
			reserva.setMedioPago(medioPagoDAO.buscarPorCodigoMedioPago(medioPago));
			reserva.setNombre(nombre);
			reserva.setEmail(email);
			reserva.setDni(dni);
			entityManager.persist(reserva);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean actualizarReserva(int reserva_id, int oferta_id, int usuario_id, int medio_de_pago_id, String nombre,
			String email, String dni) {
		try {
			Oferta oferta = ofertaDAO.buscarPorIdOferta(oferta_id);
			Reserva reserva = buscarPorIdReserva(reserva_id);
			reserva.setOferta(oferta);
			reserva.setUsuario_id(usuario_id);
			reserva.setMedioPago(medioPagoDAO.buscarPorIdMedioPago(medio_de_pago_id));
			reserva.setNombre(nombre);
			reserva.setEmail(email);
			entityManager.merge(reserva);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Reserva buscarPorIdReserva(int reserva_id) {
		try {
			return entityManager.find(Reserva.class, reserva_id);
		} catch (NoResultException nre) {
			return null;
		}
	}
}
