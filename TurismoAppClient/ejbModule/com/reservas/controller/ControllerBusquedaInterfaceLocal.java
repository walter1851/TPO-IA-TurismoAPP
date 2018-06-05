package com.reservas.controller;

import java.text.ParseException;
import java.util.List;

import com.reservas.dto.OfertaDTO;

public interface ControllerBusquedaInterfaceLocal {
	public List<OfertaDTO> buscarOfertaPaquete(String destino,int cantPersonas,String fDesde, String fHasta) throws ParseException;
	public List<OfertaDTO> buscarOfertaHotelera(String destino,int cantPersonas,String fDesde, String fHasta, String tipoHabitacion) throws ParseException;
}
