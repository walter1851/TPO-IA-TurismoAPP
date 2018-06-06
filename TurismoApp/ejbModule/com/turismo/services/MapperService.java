package com.turismo.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.turismo.dto.OfertaDTO;
import com.turismo.entities.Oferta;

@Stateless
@LocalBean
public class MapperService implements MapperServiceInterfaceLocal{
	public List<OfertaDTO> mapEntityToDto(List<Oferta> ofertas) {
		// TODO Auto-generated method stub
		return null;
	}

}
