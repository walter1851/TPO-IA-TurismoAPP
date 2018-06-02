package com.reservas.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OfertaTipo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int oferta_tipo_id;
	private String nombre;
	public int getOferta_tipo_id() {
		return oferta_tipo_id;
	}
	public void setOferta_tipo_id(int oferta_tipo_id) {
		this.oferta_tipo_id = oferta_tipo_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
