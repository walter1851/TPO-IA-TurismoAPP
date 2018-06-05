package com.reservas.services;

import java.util.List;

import javax.ejb.Local;

import com.reservas.dto.OfertaDTO;
import com.reservas.entities.Oferta;

@Local
public interface MapperServiceInterfaceLocal {
public List<OfertaDTO> mapEntityToDto(List<Oferta> ofertas);
}
