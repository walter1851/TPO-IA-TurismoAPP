package com.reservas.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Destino {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int destino_id;
	private String nombre;
	public int getDestino_id() {
		return destino_id;
	}
	public void setDestino_id(int destino_id) {
		this.destino_id = destino_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
