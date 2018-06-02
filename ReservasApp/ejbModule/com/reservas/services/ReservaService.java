package com.reservas.services;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.reservas.bean.dto.MedioPagoDTO;
import com.reservas.entities.dao.ReservaDAO;

@Stateless
@LocalBean
public class ReservaService implements ReservaServiceLocal {
	@EJB
	ReservaDAO reservaDAO;
	public void reservarPaquete(int ofertaid,String fDesde,String fHasta,int cantPersonas,String nombre,String apellido,String dni,MedioPagoDTO medioPagoDTO) {
		
	}
	public void reservarHotel(int ofertaid,String fDesde,String fHasta,String tipoHabitacion,int cantPersonas,String nombre,String apellido,String dni,MedioPagoDTO medioPagoVO) {
	
	}

}
