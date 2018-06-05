package com.reservas.services;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.reservas.dto.MedioPagoDTO;
import com.reservas.entities.dao.ReservaDAO;

@Stateless
@LocalBean
public class ReservaService implements ReservaServiceInterfaceLocal {
	@EJB
	ReservaDAO reservaDAO;
	@EJB
	ReservaDAO ofertaBloqueDAO;
	public void reservarPaquete(int ofertaid,String fDesde,String fHasta,int cantPersonas,String nombre,String apellido,String dni,MedioPagoDTO medioPagoDTO) {
		
	}
	public void reservarHotel(int ofertaid,String fDesde,String fHasta,String tipoHabitacion,int cantPersonas,String nombre,String apellido,String dni,MedioPagoDTO medioPagoVO) {
	
	}
	/*
	private boolean validarReserva() {
		//ofertaBloqueDAO.vare
		return true;
	}*/

}
