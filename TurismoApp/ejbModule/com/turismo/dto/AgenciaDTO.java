package com.turismo.dto;


public class AgenciaDTO {
	private int agencia_id;
	private String nombre;
	private String direccion;
	private String codigo_agencia;
	
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
	public String getCodigo_agencia() {
		return codigo_agencia;
	}
	public void setCodigo_agencia(String codigo_agencia) {
		this.codigo_agencia = codigo_agencia;
	}
	
}
