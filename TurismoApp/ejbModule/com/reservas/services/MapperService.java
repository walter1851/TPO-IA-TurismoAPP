package com.reservas.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.reservas.dto.OfertaDTO;
import com.reservas.entities.Oferta;

@Stateless
@LocalBean
public class MapperService implements MapperServiceInterfaceLocal{
	public List<OfertaDTO> mapEntityToDto(List<Oferta> ofertas) {
		// TODO Auto-generated method stub
		return null;
	}

}
