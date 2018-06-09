package com.turismo.coreservices;

import java.util.List;

import javax.ejb.Local;

import com.turismo.dto.OfertaDTO;
import com.turismo.entities.Oferta;

@Local
public interface MapperServiceLocal {
	public List<OfertaDTO> obtenerListOfertaDTO(List<Oferta> ofertas);
}
