package com.turismo.coreservices;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;

import com.turismo.dto.AgenciaDTO;
import com.turismo.dto.DestinoDTO;
import com.turismo.dto.EstablecimientoDTO;
import com.turismo.dto.HotelDTO;
import com.turismo.dto.MedioPagoDTO;
import com.turismo.dto.OfertaDTO;
import com.turismo.dto.OfertaTipoDTO;
import com.turismo.entities.Oferta;
import com.turismo.exceptions.OfertaHoteleraException;
import com.turismo.exceptions.OfertaPaqueteException;

@Stateless
@LocalBean
public class MapperService{
	//private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	public List<OfertaDTO> obtenerListOfertaPaqueteDTO(List<Oferta> ofertas) throws OfertaPaqueteException {
		return null;//(List<OfertaDTO>) mapper.map(ofertas, Oferta.class);
		/*
		try {
			
			List<OfertaDTO> listOfertaPaqueteDTO = new ArrayList<OfertaDTO>();
			OfertaDTO ofertaDTO;
			OfertaTipoDTO ofertaTipoDTO;
			DestinoDTO destinoDTO;
			MedioPagoDTO medioPagoDTO;
			AgenciaDTO agenciaDTO;

			for (Oferta oferta : ofertas) {
				ofertaDTO = new OfertaDTO();
				destinoDTO = new DestinoDTO();
				medioPagoDTO = new MedioPagoDTO();
				agenciaDTO = new AgenciaDTO();

				ofertaTipoDTO=OfertaTipoDTO.valueOf(oferta.getOfertaTipo().toString());
				agenciaDTO.setAgencia_id(oferta.getAgencia().getAgencia_id());
				agenciaDTO.setCodigo_agencia(oferta.getAgencia().getCodigo_agencia());
				agenciaDTO.setDireccion(oferta.getAgencia().getDireccion());
				agenciaDTO.setNombre(oferta.getAgencia().getNombre());

				medioPagoDTO.setMedio_de_pago_id(oferta.getMedioPago().getMedio_de_pago_id());
				medioPagoDTO.setNombre(oferta.getMedioPago().getNombre());

				destinoDTO.setDestino_id(oferta.getDestino().getDestino_id());
				destinoDTO.setNombre(oferta.getDestino().getNombre());

				ofertaDTO.setOferta_id(oferta.getOferta_id());
				ofertaDTO.setOfertaTipoDTO(ofertaTipoDTO);
				ofertaDTO.setDestinoDTO(destinoDTO);
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
				ofertaDTO.setPrecio(oferta.getPrecio());
				ofertaDTO.setNombre(oferta.getNombre());
				ofertaDTO.setDescripcionPaquete(oferta.getDescriptionPaquete());
				listOfertaPaqueteDTO.add(ofertaDTO);
			}
			return listOfertaPaqueteDTO;
		} catch (Exception e) {
			throw new OfertaPaqueteException("No se puede mapear list OfertaPaquete a list OfertaPaqueteDTO: " + e.getMessage());
		}*/
	}

	public List<OfertaDTO> obtenerListOfertaHoteleraDTO(List<Oferta> ofertas) throws OfertaHoteleraException {
		/*
		try {
			List<OfertaDTO> listOfertaHoteleraDTO = new ArrayList<OfertaDTO>();
			for (Oferta oferta : ofertas) {
				OfertaDTO ofertaDTO;
				OfertaTipoDTO ofertaTipoDTO;
				DestinoDTO destinoDTO;
				MedioPagoDTO medioPagoDTO;
				EstablecimientoDTO establecimientoDTO;
				HotelDTO hotelDTO;
				ofertaDTO = new OfertaDTO();
				destinoDTO = new DestinoDTO();
				establecimientoDTO = new EstablecimientoDTO();
				medioPagoDTO = new MedioPagoDTO();
				hotelDTO = new HotelDTO();

				ofertaTipoDTO=OfertaTipoDTO.valueOf(oferta.getOfertaTipo().toString());

				medioPagoDTO.setMedio_de_pago_id(oferta.getMedioPago().getMedio_de_pago_id());
				medioPagoDTO.setNombre(oferta.getMedioPago().getNombre());

				destinoDTO.setDestino_id(oferta.getDestino().getDestino_id());
				destinoDTO.setNombre(oferta.getDestino().getNombre());

				hotelDTO.setHotel_id(oferta.getEstablecimiento().getHotel().getHotel_id());
				hotelDTO.setNombre(oferta.getEstablecimiento().getHotel().getNombre());
				hotelDTO.setCodigo_hotel(oferta.getEstablecimiento().getHotel().getCodigo_hotel());

				establecimientoDTO.setCiudad(oferta.getEstablecimiento().getCiudad());
				establecimientoDTO.setCodigo_establecimiento(oferta.getEstablecimiento().getCodigo_establecimiento());
				establecimientoDTO.setDescripcion(oferta.getEstablecimiento().getDescripcion());
				establecimientoDTO.setDireccion(oferta.getEstablecimiento().getDireccion());
				establecimientoDTO.setEstablecimiento_id(oferta.getEstablecimiento().getEstablecimiento_id());
				establecimientoDTO.setEstado(oferta.getEstablecimiento().getEstado());
				establecimientoDTO.setEstrellas(oferta.getEstablecimiento().getEstrellas());
				establecimientoDTO.setHotelDTO(hotelDTO);
				establecimientoDTO.setMapa(oferta.getEstablecimiento().getMapa());
				establecimientoDTO.setNombre(oferta.getEstablecimiento().getNombre());
				ofertaDTO.setOferta_id(oferta.getOferta_id());
				ofertaDTO.setDestinoDTO(destinoDTO);
				ofertaDTO.setEstablecimientoDTO(establecimientoDTO);
				ofertaDTO.setOfertaTipoDTO(ofertaTipoDTO);
				ofertaDTO.setFecha_desde(oferta.getFecha_desde());
				ofertaDTO.setFecha_hasta(oferta.getFecha_hasta());
				ofertaDTO.setMedioPagoDTO(medioPagoDTO);
				ofertaDTO.setPoliticas(oferta.getPoliticas());
				ofertaDTO.setServicios(oferta.getServicios());
				ofertaDTO.setCant_personas(oferta.getCant_personas());
				ofertaDTO.setCupo(oferta.getCupo());
				ofertaDTO.setServicios(oferta.getServicios());
				ofertaDTO.setTipo_habitacion(oferta.getTipo_habitacion());
				ofertaDTO.setPrecio(oferta.getPrecio());
				ofertaDTO.setNombre(oferta.getNombre());
				listOfertaHoteleraDTO.add(ofertaDTO);
			}
			return listOfertaHoteleraDTO;
		} catch (Exception e) {
			throw new OfertaHoteleraException(
					"No se puede mapear list OfertaHotelera a list OfertaHoteleraDTO. Detalle: " + e.getMessage());
		}*/
		return null;//(List<OfertaDTO>) mapper.map(ofertas, Oferta.class);
	}	
		
}
