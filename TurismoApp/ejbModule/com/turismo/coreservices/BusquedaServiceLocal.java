package com.turismo.coreservices;

import java.util.List;

import javax.ejb.Local;

import com.turismo.dto.OfertaDTO;
import com.turismo.exceptions.OfertaHoteleraException;
import com.turismo.exceptions.OfertaPaqueteException;

@Local
public interface BusquedaServiceLocal {
	public List<OfertaDTO> buscarOfertaPaquete(String destino,int cantPersonas,String fDesde, String fHasta) throws OfertaPaqueteException;
	public List<OfertaDTO> buscarOfertaHotelera(String destino,int cantPersonas,String fDesde, String fHasta,String tipoHabitacion) throws OfertaHoteleraException;
}
