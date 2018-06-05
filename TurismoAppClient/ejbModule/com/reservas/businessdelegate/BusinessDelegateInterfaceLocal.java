package com.reservas.businessdelegate;

import java.text.ParseException;
import java.util.List;
import javax.ejb.Local;

import com.reservas.dto.MedioPagoDTO;
import com.reservas.dto.OfertaDTO;

@Local
public interface BusinessDelegateInterfaceLocal {
	public void reservarHotel(int ofertaid, String fDesde, String fHasta, String tipoHabitacion, int cantPersonas,
			String nombre, String apellido, String dni, String medioPago);
	public void reservarPaquete(int ofertaid, String fDesde, String fHasta, int cantPersonas, String nombre,
			String apellido, String dni, String medioPago);
	public List<OfertaDTO> buscarOfertaHotelera(String destino, int cantPersonas, String fDesde, String fHasta,
			String tipoHabitacion) throws ParseException;
	public List<OfertaDTO> buscarOfertaPaquete(String destino, int cantPersonas, String fDesde, String fHasta) throws ParseException;
}