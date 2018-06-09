package com.turismo.coreservices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.turismo.coreservices.ReservaServiceLocal;
import com.turismo.dao.OfertaBloqueDAO;
import com.turismo.dao.ReservaDAO;
import com.turismo.entities.OfertaBloque;
import com.turismo.exceptions.ReservaException;

@Stateless
@LocalBean
public class ReservaService implements ReservaServiceLocal {
	@EJB
	private ReservaDAO reservaDAO;
	@EJB
	private OfertaBloqueDAO ofertaBloqueDAO;
	public void reservarPaquete(int ofertaid,String fDesde,String fHasta,int cantPersonas,String nombre,String apellido,String dni,String medioPago) throws ReservaException {
	
	}
	public void reservarHotel(int ofertaid,String fDesde,String fHasta,String tipoHabitacion,int cantPersonas,String nombre,String apellido,String dni,String medioPago) throws ReservaException {
		boolean fechasValidas=validarReserva();
		List<OfertaBloque> bloques = ofertaBloqueDAO.buscarBloques(ofertaid,fDesde, fHasta, cantPersonas);
		boolean hayDisponibilidad=this.validarDisponibilidad(bloques);
		boolean puedoReservar=true/*tengo que preguntarle si puedo reservar al backoffice*/;

		if (!fechasValidas)
				throw new ReservaException("La fecha ingresada no tiene el formato adecuado.");
		if (!hayDisponibilidad)	
			throw new ReservaException("No hay disponibilidad desde la fecha "+fDesde+" hasta "+fHasta+" para la cantidad de "+cantPersonas+" persona/s");
		if (!puedoReservar)		
			throw new ReservaException("No hay autorizacion del backoffice para reservar");		
			
		if (fechasValidas&&hayDisponibilidad&&puedoReservar) {
			for (OfertaBloque ofertaBloque: bloques) {
				//Descontamos uno al cupo
				ofertaBloque.setCupo(ofertaBloque.getCupo()-1);
				ofertaBloqueDAO.actualizarBloque(ofertaBloque);
				//crear reserva
			}
		}		
	}
	private boolean validarDisponibilidad(List<OfertaBloque> bloques) {
		// Verfico que exista cupo mayor o igual a uno para cada uno de los bloques
		if (bloques.isEmpty())
			return false;
		else {
			boolean disponibilidad = true;
			for (OfertaBloque ofertaBloque : bloques) {
				if (ofertaBloque.getCupo() == 0) {
					disponibilidad = false;
				}
			}
			return true;
		}
	}
	private boolean validarReserva() {
		//despues lo completo
		return true;
	}
}
