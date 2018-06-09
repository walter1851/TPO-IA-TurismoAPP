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
		OfertaTipoDTO ofertaTipoDTO;
	    DestinoDTO destinoDTO;
	    MedioPagoDTO medioPagoDTO;
	    EstablecimientoDTO establecimientoDTO;
		AgenciaDTO agenciaDTO;

		for (Oferta oferta : ofertas) {
			ofertaDTO = new OfertaDTO();
			destinoDTO = new DestinoDTO();
			establecimientoDTO=new EstablecimientoDTO();
			medioPagoDTO=new MedioPagoDTO();
			agenciaDTO=new AgenciaDTO();
			ofertaTipoDTO=new OfertaTipoDTO();
			
			ofertaTipoDTO.setNombre(oferta.getOfertaTipo().getNombre());
			ofertaTipoDTO.setOferta_tipo_id(oferta.getOfertaTipo().getOferta_tipo_id());
			
			agenciaDTO.setAgencia_id(oferta.getAgencia().getAgencia_id());
			agenciaDTO.setCodigo_agencia(oferta.getAgencia().getCodigo_agencia());
			agenciaDTO.setDireccion(oferta.getAgencia().getDireccion());
			agenciaDTO.setNombre(oferta.getAgencia().getNombre());
			
			medioPagoDTO.setCodigo(oferta.getMedioPago().getCodigo());
			medioPagoDTO.setMedio_de_pago_id(oferta.getMedioPago().getMedio_de_pago_id());
			medioPagoDTO.setNombre(oferta.getMedioPago().getNombre());
			
			destinoDTO.setDestino_id(oferta.getDestino().getDestino_id());
			destinoDTO.setNombre(oferta.getDestino().getNombre());
			
			establecimientoDTO.setCiudad(oferta.getEstablecimiento().getCiudad());
			establecimientoDTO.setCodigo_establecimiento(oferta.getEstablecimiento().getCodigo_establecimiento());
			
			ofertaDTO.setOferta_id(oferta.getOferta_id());
			ofertaDTO.setAgenciaDTO(agenciaDTO);
			ofertaDTO.setDestinoDTO(destinoDTO);
			ofertaDTO.setEstablecimientoDTO(establecimientoDTO);
			ofertaDTO.setOfertaTipoDTO(ofertaTipoDTO);
			ofertaDTO.setFecha_desde(oferta.getFecha_desde());
			ofertaDTO.setFecha_hasta(oferta.getFecha_hasta());
			ofertaDTO.setMedioPagoDTO(medioPagoDTO);
			ofertaDTO.setFoto_paquete(oferta.getFoto_paquete());
			ofertaDTO.setPoliticas(oferta.getPoliticas());
			ofertaDTO.setServicios(oferta.getServicios());
			ofertaDTO.setCant_personas(oferta.getCant_personas());
			ofertaDTO.setCupo(oferta.getCupo());
			ofertaDTO.setServicios(oferta.getServicios());
			ofertaDTO.setTipo_habitacion(oferta.getTipo_habitacion());
			listOfertaDTO.add(ofertaDTO);
		}
		return listOfertaDTO;
	}
}
