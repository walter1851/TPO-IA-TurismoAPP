package com.turismo.services;

import java.util.List;

import javax.ejb.Local;

import com.turismo.dto.OfertaDTO;
import com.turismo.entities.Oferta;

@Local
public interface MapperServiceInterfaceLocal {
public List<OfertaDTO> mapEntityToDto(List<Oferta> ofertas);
}
