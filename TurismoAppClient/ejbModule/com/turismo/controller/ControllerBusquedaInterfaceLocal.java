package com.turismo.controller;

import java.text.ParseException;
import java.util.List;

import com.turismo.dto.OfertaDTO;

public interface ControllerBusquedaInterfaceLocal {
	public List<OfertaDTO> buscarOfertaPaquete(String destino,int cantPersonas,String fDesde, String fHasta) throws ParseException;
	public List<OfertaDTO> buscarOfertaHotelera(String destino,int cantPersonas,String fDesde, String fHasta, String tipoHabitacion) throws ParseException;
}
