package com.turismo.dto;

import java.time.LocalDate;


public class OfertaBloqueDTO {
	private Integer oferta_bloque_id;
	private OfertaDTO ofertaDTO;
	private LocalDate fecha_bloque;
	private Integer cupo;
	public Integer getOferta_bloque_id() {
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
	public LocalDate getFecha_bloque() {
		return fecha_bloque;
	}
	public void setFecha_bloque(LocalDate fecha_bloque) {
		this.fecha_bloque = fecha_bloque;
	}
	public Integer getCupo() {
		return cupo;
	}
	public void setCupo(Integer cupo) {
		this.cupo = cupo;
	}
	
}
