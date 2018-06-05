package com.reservas.controller;

import java.text.ParseException;
import java.util.List;

import javax.ejb.EJB;

import com.reservas.dto.OfertaDTO;
import com.reservas.services.BusquedaService;

public class ControllerBusqueda implements ControllerBusquedaInterfaceLocal{
	@EJB
	BusquedaService busquedaOfertaService;
	
	public List<OfertaDTO> buscarOfertaPaquete(String destino,int cantPersonas,String fDesde, String fHasta) throws ParseException{
		return busquedaOfertaService.buscarOfertaPaquete(destino, cantPersonas, fDesde, fHasta);
	}

	public List<OfertaDTO> buscarOfertaHotelera(String destino,int cantPersonas,String fDesde, String fHasta, String tipoHabitacion) throws ParseException {
		return busquedaOfertaService.buscarOfertaHotelera(destino, cantPersonas, fDesde, fHasta, tipoHabitacion);
	}
}
