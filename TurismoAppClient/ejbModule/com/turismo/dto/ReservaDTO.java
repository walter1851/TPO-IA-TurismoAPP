package com.turismo.dto;

import java.util.List;

import com.turismo.dto.MedioPagoDTO;
import com.turismo.dto.OfertaDTO;


public class ReservaDTO {
	private int reserva_id;
	private OfertaDTO oferta;
	protected List<UsuarioDTO> usuarios;
	private MedioPagoDTO medioPago;
	public int getReserva_id() {
		return reserva_id;
	}
	public void setReserva_id(int reserva_id) {
		this.reserva_id = reserva_id;
	}
	public OfertaDTO getOferta() {
		return oferta;
	}
	public void setOferta(OfertaDTO oferta) {
		this.oferta = oferta;
	}
	public List<UsuarioDTO> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<UsuarioDTO> usuarios) {
		this.usuarios = usuarios;
	}
	public MedioPagoDTO getMedioPago() {
		return medioPago;
	}
	public void setMedioPago(MedioPagoDTO medioPago) {
		this.medioPago = medioPago;
	}
}
