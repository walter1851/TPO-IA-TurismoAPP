package com.turismo.coreservices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.turismo.dao.OfertaBloqueDAO;
import com.turismo.dao.ReservaDAO;
import com.turismo.entities.OfertaBloque;
import com.turismo.exceptions.ReservaException;

@Stateless
@LocalBean
public class ReservaService{
	@EJB
	private ReservaDAO reservaDAO;
	@EJB
	private OfertaBloqueDAO ofertaBloqueDAO;
	public boolean reservarPaquete(int ofertaid,String fDesde,String fHasta,int cantPersonas,String nombre,String apellido,String dni,String medioPago,String emailUsuario) throws ReservaException {
				//valido el formato de las fechas
				boolean reservaOK=false;
				boolean fechasValidas=validarReserva();
				//obtengo todos los bloques que coinciden para validar la disponiblidad
				List<OfertaBloque> bloques = ofertaBloqueDAO.buscarBloques(ofertaid,fDesde, fHasta, cantPersonas);
				boolean hayDisponibilidad=this.validarDisponibilidad(bloques);
				//consulto al backoffice si puedo reservar
				boolean puedoReservar=true/*completar despues*/;

				if (!fechasValidas)
						throw new ReservaException("La fecha ingresada no tiene el formato adecuado.");
				if (!hayDisponibilidad)	
					throw new ReservaException("No hay disponibilidad desde la fecha "+fDesde+" hasta "+fHasta+" para la cantidad de "+cantPersonas+" persona/s");
				if (!puedoReservar)		
					throw new ReservaException("No hay autorizacion del backoffice para reservar");		
					
				if (fechasValidas&&hayDisponibilidad&&puedoReservar) {
					boolean cupoActualizado=true;
					for (OfertaBloque ofertaBloque: bloques) {
						//Descontamos uno al cupo
						ofertaBloque.setCupo(ofertaBloque.getCupo()-1);
						if (!ofertaBloqueDAO.actualizarBloque(ofertaBloque))
							cupoActualizado=false;
					}
					if (cupoActualizado) 
						reservaOK=reservaDAO.crearReserva(ofertaid, 1, medioPago, nombre, emailUsuario, dni);
					else 
						throw new ReservaException("No se pudo actualizar el cupo.");	
				}
				return reservaOK;
	}
	public boolean reservarHotel(int ofertaid,String fDesde,String fHasta,String tipoHabitacion,int cantPersonas,String nombre,String apellido,String dni,String medioPago,String emailUsuario) throws ReservaException {
		boolean reservaOK=false;
		//valido el formato de las fechas
		boolean fechasValidas=validarReserva();
		//obtengo todos los bloques que coinciden para validar la disponiblidad
		List<OfertaBloque> bloques = ofertaBloqueDAO.buscarBloques(ofertaid,fDesde, fHasta, cantPersonas,tipoHabitacion);
		boolean hayDisponibilidad=this.validarDisponibilidad(bloques);
		//consulto al backoffice si puedo reservar
		boolean puedoReservar=true/*completar despues*/;

		if (!fechasValidas)
				throw new ReservaException("La fecha ingresada no tiene el formato adecuado.");
		if (!hayDisponibilidad)	
			throw new ReservaException("No hay disponibilidad desde la fecha "+fDesde+" hasta "+fHasta+" para la cantidad de "+cantPersonas+" persona/s");
		if (!puedoReservar)		
			throw new ReservaException("No hay autorizacion del backoffice para reservar");		
			
		if (fechasValidas&&hayDisponibilidad&&puedoReservar) {
			boolean cupoActualizado=true;
			for (OfertaBloque ofertaBloque: bloques) {
				//Descontamos uno al cupo
				ofertaBloque.setCupo(ofertaBloque.getCupo()-1);
				if (!ofertaBloqueDAO.actualizarBloque(ofertaBloque))
					cupoActualizado=false;
			}
			if (cupoActualizado) 
				reservaOK=reservaDAO.crearReserva(ofertaid, 1, medioPago, nombre, emailUsuario, dni);
			else 
				throw new ReservaException("No se pudo actualizar el cupo.");	
		}
		return reservaOK;
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
			return disponibilidad;
		}
	}
	private boolean validarReserva() {
		//despues lo completo
		return true;
	}
}
