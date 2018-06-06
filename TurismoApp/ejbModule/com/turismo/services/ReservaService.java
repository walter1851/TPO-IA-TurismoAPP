package com.turismo.services;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.turismo.dao.impl.ReservaDAO;
import com.turismo.dto.MedioPagoDTO;
import com.turismo.services.ReservaServiceInterfaceLocal;

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
}
