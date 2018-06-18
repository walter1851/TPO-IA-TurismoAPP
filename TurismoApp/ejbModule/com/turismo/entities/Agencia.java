package com.turismo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="agencias") 
public class Agencia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int agencia_id;
	private String nombre;
	private String direccion;
	@Column(unique = true)
	private int codigo_agencia;

	public int getAgencia_id() {
		return agencia_id;
	}

	public void setAgencia_id(int agencia_id) {
		this.agencia_id = agencia_id;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getCodigo_agencia() {
		return codigo_agencia;
	}
	public void setCodigo_agencia(int codigo_agencia) {
		this.codigo_agencia = codigo_agencia;
	}
}
