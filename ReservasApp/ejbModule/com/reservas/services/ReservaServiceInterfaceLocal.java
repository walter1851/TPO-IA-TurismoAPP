package com.reservas.services;

import javax.ejb.Local;

import com.reservas.dto.MedioPagoDTO;

@Local
public interface ReservaServiceInterfaceLocal {
	public void reservarPaquete(int ofertaid, String fDesde, String fHasta, int cantPersonas, String nombre,
			String apellido, String dni, MedioPagoDTO medioPagoDTO);

	public void reservarHotel(int ofertaid, String fDesde, String fHasta, String tipoHabitacion, int cantPersonas,
			String nombre, String apellido, String dni, MedioPagoDTO medioPagoVO);
}
