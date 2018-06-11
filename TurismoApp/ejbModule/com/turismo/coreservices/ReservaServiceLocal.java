package com.turismo.coreservices;

import javax.ejb.Local;

import com.turismo.exceptions.ReservaException;


@Local
public interface ReservaServiceLocal {
	public boolean reservarPaquete(int ofertaid, String fDesde, String fHasta, int cantPersonas, String nombre,
			String apellido, String dni, String medioPago,String emailUsuario) throws ReservaException;

	public boolean reservarHotel(int ofertaid, String fDesde, String fHasta, String tipoHabitacion, int cantPersonas,
			String nombre, String apellido, String dni, String medioPago,String emailUsuario) throws ReservaException;
}
