package com.turismo.coreservices;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.turismo.dto.AgenciaDTO;
import com.turismo.dto.DestinoDTO;
import com.turismo.dto.EstablecimientoDTO;
import com.turismo.dto.EstadoDTO;
import com.turismo.dto.HotelDTO;
import com.turismo.dto.MedioPagoDTO;
import com.turismo.dto.OfertaDTO;
import com.turismo.dto.OfertaTipoDTO;
import com.turismo.entities.Oferta;
import com.turismo.exceptions.OfertaHoteleraException;
import com.turismo.exceptions.OfertaPaqueteException;

@Stateless
@LocalBean
public class MapperService {
	public List<OfertaDTO> obtenerListaOfertaPaqueteDTO(List<Oferta> ofertas) throws OfertaPaqueteException {
		try {
			List<OfertaDTO> listOfertaPaqueteDTO = new ArrayList<OfertaDTO>();
			for (Oferta oferta : ofertas) {
				OfertaDTO ofertaDTO = new OfertaDTO();
				
				OfertaTipoDTO ofertaTipoDTO = OfertaTipoDTO.valueOf(oferta.getOfertaTipo().name());
				
				AgenciaDTO agenciaDTO = new AgenciaDTO();
				agenciaDTO.setAgencia_id(oferta.getAgencia().getAgencia_id());
				agenciaDTO.setCodigo_agencia(oferta.getAgencia().getCodigo_agencia());
				agenciaDTO.setDireccion(oferta.getAgencia().getDireccion());
				agenciaDTO.setNombre(oferta.getAgencia().getNombre());
				
				MedioPagoDTO medioPagoDTO = new MedioPagoDTO();
				medioPagoDTO.setMedio_de_pago_id(oferta.getMedioPago().getMedio_de_pago_id());
				medioPagoDTO.setNombre(oferta.getMedioPago().getNombre());

				DestinoDTO destinoDTO = new DestinoDTO();
				destinoDTO.setDestino_id(oferta.getDestino().getDestino_id());
				destinoDTO.setNombre(oferta.getDestino().getNombre());

				ofertaDTO.setOferta_id(oferta.getOferta_id());
				ofertaDTO.setOfertaTipoDTO(ofertaTipoDTO);
				ofertaDTO.setDestinoDTO(destinoDTO);				
				ofertaDTO.setMedioPagoDTO(medioPagoDTO);
				ofertaDTO.setFecha_desde(oferta.getFecha_desde());
				ofertaDTO.setFecha_hasta(oferta.getFecha_hasta());
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
			throw new OfertaPaqueteException(
					"No se puede mapear la lista de OfertaPaquete a list OfertaPaqueteDTO: " + e.getMessage());
		}
	}

	public List<OfertaDTO> obtenerListaOfertaHoteleraDTO(List<Oferta> ofertas) throws OfertaHoteleraException {
		try {
			List<OfertaDTO> listOfertaHoteleraDTO = new ArrayList<OfertaDTO>();
			for (Oferta oferta : ofertas) {
				EstablecimientoDTO establecimientoDTO;
				OfertaDTO ofertaDTO = new OfertaDTO();
				DestinoDTO destinoDTO = new DestinoDTO();
				establecimientoDTO = new EstablecimientoDTO();
				MedioPagoDTO medioPagoDTO = new MedioPagoDTO();
				HotelDTO hotelDTO = new HotelDTO();

				OfertaTipoDTO ofertaTipoDTO = OfertaTipoDTO.valueOf(oferta.getOfertaTipo().toString());

				medioPagoDTO.setMedio_de_pago_id(oferta.getMedioPago().getMedio_de_pago_id());
				medioPagoDTO.setNombre(oferta.getMedioPago().getNombre());

				destinoDTO.setDestino_id(oferta.getDestino().getDestino_id());
				destinoDTO.setNombre(oferta.getDestino().getNombre());

				hotelDTO.setHotel_id(oferta.getEstablecimiento().getHotel().getHotel_id());
				hotelDTO.setNombre(oferta.getEstablecimiento().getHotel().getNombre());
				hotelDTO.setCodigo_hotel(oferta.getEstablecimiento().getHotel().getCodigo_hotel());

				EstadoDTO estadoDTO = EstadoDTO.valueOf(oferta.getEstablecimiento().getEstado().name());
				establecimientoDTO.setEstado(estadoDTO);
				establecimientoDTO.setCiudad(oferta.getEstablecimiento().getCiudad());
				establecimientoDTO.setCodigo_establecimiento(oferta.getEstablecimiento().getCodigo_establecimiento());
				establecimientoDTO.setDescripcion(oferta.getEstablecimiento().getDescripcion());
				establecimientoDTO.setDireccion(oferta.getEstablecimiento().getDireccion());
				establecimientoDTO.setEstablecimiento_id(oferta.getEstablecimiento().getEstablecimiento_id());
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
					"No se puede mapear la lista de entidades de negocio OfertaHotelera a la lista OfertaHoteleraDTOs. Detalle: " + e.getMessage());
		}
	}

}
