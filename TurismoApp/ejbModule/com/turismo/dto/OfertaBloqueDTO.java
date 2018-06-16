package com.turismo.dto;

import java.time.LocalDateTime;


public class OfertaBloqueDTO {
	private int oferta_bloque_id;
	private OfertaDTO ofertaDTO;
	private LocalDateTime fecha_bloque;
	private int cupo;
	public int getOferta_bloque_id() {
		return oferta_bloque_id;
	}
	public void setOferta_bloque_id(int oferta_bloque_id) {
		this.oferta_bloque_id = oferta_bloque_id;
	}
	public OfertaDTO getOfertaDTO() {
		return ofertaDTO;
	}
	public void setOfertaDTO(OfertaDTO ofertaDTO) {
		this.ofertaDTO = ofertaDTO;
	}
	public LocalDateTime getFecha_bloque() {
		return fecha_bloque;
	}
	public void setFecha_bloque(LocalDateTime fecha_bloque) {
		this.fecha_bloque = fecha_bloque;
	}
	public int getCupo() {
		return cupo;
	}
	public void setCupo(int cupo) {
		this.cupo = cupo;
	}
	
}
