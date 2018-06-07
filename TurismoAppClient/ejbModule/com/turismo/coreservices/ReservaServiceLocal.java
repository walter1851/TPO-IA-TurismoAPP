package com.turismo.coreservices;

import javax.ejb.Local;

import com.turismo.dto.MedioPagoDTO;

@Local
public interface ReservaServiceLocal {
	public void reservarPaquete(int ofertaid, String fDesde, String fHasta, int cantPersonas, String nombre,
			String apellido, String dni, String medioPago);

	public void reservarHotel(int ofertaid, String fDesde, String fHasta, String tipoHabitacion, int cantPersonas,
			String nombre, String apellido, String dni, String medioPago);
}
