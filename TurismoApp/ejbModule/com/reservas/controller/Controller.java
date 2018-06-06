package com.reservas.controller;

import java.text.ParseException;
import java.util.List;

import javax.ejb.EJB;

import com.reservas.dto.OfertaDTO;
import com.reservas.services.BusquedaService;
import com.reservas.services.ReservaService;

public class Controller implements ControllerBusquedaInterfaceLocal{
	@EJB
	BusquedaService busquedaOfertaService;
	@EJB
	ReservaService reservaService;
	
	public List<OfertaDTO> buscarOfertaPaquete(String destino,int cantPersonas,String fDesde, String fHasta) throws ParseException{
		return busquedaOfertaService.buscarOfertaPaquete(destino, cantPersonas, fDesde, fHasta);
	}

	public List<OfertaDTO> buscarOfertaHotelera(String destino,int cantPersonas,String fDesde, String fHasta, String tipoHabitacion) throws ParseException {
		return busquedaOfertaService.buscarOfertaHotelera(destino, cantPersonas, fDesde, fHasta, tipoHabitacion);
	}
	public void reservarHotel(int ofertaid,String fDesde,String fHasta,String tipoHabitacion,int cantPersonas,String nombre,String apellido,String dni,String medioPago) {
		reservaService.reservarHotel(ofertaid, fDesde, fHasta, tipoHabitacion, cantPersonas, nombre, apellido, dni, medioPago);
	}
	public void reservarPaquete(int ofertaid,String fDesde,String fHasta,int cantPersonas,String nombre,String apellido,String dni,String medioPago) {
		reservaService.reservarPaquete(ofertaid, fDesde, fHasta, cantPersonas, nombre, apellido, dni, medioPago);
	}
}
