package com.reservas.services;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.reservas.dao.impl.ReservaDAO;
import com.reservas.dto.MedioPagoDTO;
import com.reservas.services.ReservaServiceInterfaceLocal;

@Stateless
@LocalBean
public class ReservaService implements ReservaServiceInterfaceLocal {
	@EJB
	ReservaDAO reservaDAO;
	@EJB
	ReservaDAO ofertaBloqueDAO;
	public void reservarPaquete(int ofertaid,String fDesde,String fHasta,int cantPersonas,String nombre,String apellido,String dni,String medioPago) {
		
	}
	public void reservarHotel(int ofertaid,String fDesde,String fHasta,String tipoHabitacion,int cantPersonas,String nombre,String apellido,String dni,String medioPago) {
	
	}
	/*
	private boolean validarReserva() {
		//ofertaBloqueDAO.vare
		return true;
	}*/

}
