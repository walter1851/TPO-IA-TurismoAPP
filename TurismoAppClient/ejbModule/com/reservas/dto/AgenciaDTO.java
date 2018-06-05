package com.reservas.dto;

public class AgenciaDTO {
	private int id;
	private String nombre;
	private String direccion;
	private String codigo_agencia;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
