package com.turismo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="destinos") 
public class Destino {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int destino_id;
	private String nombre;
	@Column(unique = true)
	private int codigo_destino;
	public int getDestino_id() {
		return destino_id;
	}
	public void setDestino_id(int destino_id) {
		this.destino_id = destino_id;
	}
	public int getCodigo_destino() {
		return codigo_destino;
	}
	public void setCodigo_destino(int codigo_destino) {
		this.codigo_destino = codigo_destino;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
