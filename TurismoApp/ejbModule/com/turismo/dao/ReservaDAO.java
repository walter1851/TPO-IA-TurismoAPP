package com.turismo.dao;

import java.time.LocalDate;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

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

	public Reserva crearReserva(int oferta_id, LocalDate fechaInicio, LocalDate fechaFin,int id_medio_pago, String nombre, String apellido, String email,
			String dni,float montoTotal) {
		try {
			Oferta oferta=ofertaDAO.buscarPorIdOferta(oferta_id);
			Reserva reserva = new Reserva();
			reserva.setOferta(oferta);
			reserva.setMedioPago(medioPagoDAO.buscarPorIdMedioPago(id_medio_pago));
			reserva.setNombre(nombre);
			reserva.setApellido(apellido);
			reserva.setEmail(email);
			reserva.setDni(dni);
			reserva.setMontoTotal(montoTotal);
			reserva.setFechaInicio(fechaInicio);
			reserva.setFechaFin(fechaFin);
			entityManager.persist(reserva);
			return reserva;
		} catch (PersistenceException e) {
			return null;
		}
	}

	public boolean actualizarReserva(int reserva_id, LocalDate fechaInicio, LocalDate fechaFin,int oferta_id, int usuario_id, int medio_de_pago_id, String nombre,
			String email, String dni, float montoTotal) {
		try {
			Oferta oferta = ofertaDAO.buscarPorIdOferta(oferta_id);
			Reserva reserva = buscarPorIdReserva(reserva_id);
			reserva.setOferta(oferta);
			reserva.setMedioPago(medioPagoDAO.buscarPorIdMedioPago(medio_de_pago_id));
			reserva.setNombre(nombre);
			reserva.setEmail(email);
			reserva.setMontoTotal(montoTotal);
			reserva.setFechaInicio(fechaInicio);
			reserva.setFechaFin(fechaFin);
			entityManager.merge(reserva);
			return true;
		} catch (PersistenceException e) {
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
