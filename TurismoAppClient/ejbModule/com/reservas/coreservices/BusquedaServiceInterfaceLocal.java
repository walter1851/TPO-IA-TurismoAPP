package com.reservas.coreservices;

import java.text.ParseException;
import java.util.List;

import javax.ejb.Local;

import com.reservas.dto.OfertaDTO;

@Local
public interface BusquedaServiceInterfaceLocal {
	public List<OfertaDTO> buscarOfertaPaquete(String destino,int cantPersonas,String fDesde, String fHasta) throws ParseException;
	public List<OfertaDTO> buscarOfertaHotelera(String destino,int cantPersonas,String fDesde, String fHasta,String tipoHabitacion) throws ParseException;
}
