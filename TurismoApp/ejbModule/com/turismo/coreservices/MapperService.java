package com.turismo.coreservices;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.turismo.coreservices.MapperServiceLocal;
import com.turismo.dto.AgenciaDTO;
import com.turismo.dto.DestinoDTO;
import com.turismo.dto.EstablecimientoDTO;
import com.turismo.dto.MedioPagoDTO;
import com.turismo.dto.OfertaDTO;
import com.turismo.dto.OfertaTipoDTO;
import com.turismo.entities.Oferta;



@Stateless
@LocalBean
public class MapperService implements MapperServiceLocal{
	public List<OfertaDTO> obtenerListOfertaDTO(List<Oferta> ofertas) {
		List<OfertaDTO> listOfertaDTO = new ArrayList<OfertaDTO>();
		OfertaDTO ofertaDTO;
	    DestinoDTO destinoDTO;
	    MedioPagoDTO medioPagoDTO;
	    EstablecimientoDTO establecimientoDTO;
		AgenciaDTO agenciaDTO;
		OfertaTipoDTO ofertaTipoDTO;
		
		for (Oferta oferta : ofertas) {
			ofertaDTO = new OfertaDTO();
			destinoDTO = new DestinoDTO();
			establecimientoDTO=new EstablecimientoDTO();
			medioPagoDTO=new MedioPagoDTO();
			agenciaDTO=new AgenciaDTO();
			
			destinoDTO.setDestino_id(oferta.getDestino().getDestino_id());
			destinoDTO.setNombre(oferta.getDestino().getNombre());
			
			establecimientoDTO.setCiudad(oferta.getEstablecimiento().getCiudad());
			establecimientoDTO.setCodigo_establecimiento(oferta.getEstablecimiento().getCodigo_establecimiento());
			
			ofertaDTO.setOferta_id(oferta.getOferta_id());
			ofertaDTO.setAgenciaDTO(agenciaDTO);
			ofertaDTO.setCant_personas(oferta.getCant_personas());
			ofertaDTO.setCupo(oferta.getCupo());
			ofertaDTO.setServicios(oferta.getServicios());
			listOfertaDTO.add(ofertaDTO);
		}
		return listOfertaDTO;
	}
}
