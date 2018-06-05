package com.reservas.businessdelegate;

import java.text.ParseException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.reservas.controller.ControllerBusqueda;
import com.reservas.controller.ControllerReserva;
import com.reservas.dto.MedioPagoDTO;
import com.reservas.dto.OfertaDTO;

@Stateless
@LocalBean
public class BusinessDelegate implements BusinessDelegateInterfaceLocal{
	@EJB
	ControllerReserva controllerReserva;
	@EJB
	ControllerBusqueda controllerBusqueda;
	
	public void reservarHotel(int ofertaid, String fDesde, String fHasta, String tipoHabitacion, int cantPersonas,
			String nombre, String apellido, String dni, MedioPagoDTO medioPagoDTO) {
		controllerReserva.reservarHotel(ofertaid, fDesde, fHasta, tipoHabitacion, cantPersonas, nombre, apellido, dni, medioPagoDTO);
	}

	public void reservarPaquete(int ofertaid, String fDesde, String fHasta, int cantPersonas, String nombre,
			String apellido, String dni, MedioPagoDTO medioPagoDTO) {
		controllerReserva.reservarPaquete(ofertaid, fDesde, fHasta, cantPersonas, nombre, apellido, dni, medioPagoDTO);
	}

	public List<OfertaDTO> buscarOfertaHotelera(String destino, int cantPersonas, String fDesde, String fHasta,
			String tipoHabitacion) throws ParseException {
		return controllerBusqueda.buscarOfertaHotelera(destino, cantPersonas, fDesde, fHasta, tipoHabitacion);
	}

	public List<OfertaDTO> buscarOfertaPaquete(String destino, int cantPersonas, String fDesde, String fHasta) throws ParseException {
		return controllerBusqueda.buscarOfertaPaquete(destino, cantPersonas, fDesde, fHasta);
	}
}
