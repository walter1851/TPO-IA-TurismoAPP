package com.reservas.controller;

import java.text.ParseException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.reservas.coreservices.BusquedaService;
import com.reservas.coreservices.ReservaService;
import com.reservas.dto.MedioPagoDTO;
import com.reservas.dto.OfertaDTO;

@Stateless
@LocalBean
public class ControllerReserva implements ControllerReservaInterfaceLocal{
	@EJB
	ReservaService reservaService;

	public void reservarHotel(int ofertaid,String fDesde,String fHasta,String tipoHabitacion,int cantPersonas,String nombre,String apellido,String dni,MedioPagoDTO medioPagoDTO) {
		reservaService.reservarHotel(ofertaid, fDesde, fHasta, tipoHabitacion, cantPersonas, nombre, apellido, dni, medioPagoDTO);
	}
	public void reservarPaquete(int ofertaid,String fDesde,String fHasta,int cantPersonas,String nombre,String apellido,String dni,MedioPagoDTO medioPagoDTO) {
		reservaService.reservarPaquete(ofertaid, fDesde, fHasta, cantPersonas, nombre, apellido, dni, medioPagoDTO);
	}
}
