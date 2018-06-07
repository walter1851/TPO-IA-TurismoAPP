package com.turismo.coreservices;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.turismo.coreservices.ReservaServiceLocal;
import com.turismo.dao.impl.ReservaDAO;
import com.turismo.dto.MedioPagoDTO;

@Stateless
@LocalBean
public class ReservaService implements ReservaServiceLocal {
	@EJB
	private ReservaDAO reservaDAO;
	@EJB
	private ReservaDAO ofertaBloqueDAO;
	public void reservarPaquete(int ofertaid,String fDesde,String fHasta,int cantPersonas,String nombre,String apellido,String dni,String medioPago) {
	
	}
	public void reservarHotel(int ofertaid,String fDesde,String fHasta,String tipoHabitacion,int cantPersonas,String nombre,String apellido,String dni,String medioPago) {
	
	}
}
