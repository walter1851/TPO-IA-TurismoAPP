package com.turismo.coreservices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.turismo.dto.OfertaDTO;
import com.turismo.entities.Oferta;
import org.dozer.Mapper;
import org.dozer.MappingException;

@Stateless
@LocalBean
public class MapperService implements Mapper{
	@EJB
	private Mapper mapper;
	public List<OfertaDTO> mapEntityToDto(List<Oferta> ofertas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T map(Object arg0, Class<T> arg1) throws MappingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void map(Object arg0, Object arg1) throws MappingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <T> T map(Object arg0, Class<T> arg1, String arg2) throws MappingException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void map(Object arg0, Object arg1, String arg2) throws MappingException {
		// TODO Auto-generated method stub
		
	}

}
