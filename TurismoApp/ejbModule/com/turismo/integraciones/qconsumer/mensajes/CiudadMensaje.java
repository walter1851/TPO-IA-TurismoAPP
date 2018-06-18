package com.turismo.integraciones.qconsumer.mensajes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CiudadMensaje {
	@JsonProperty("id")
	private int codigo_ciudad;
	//private String nombre;

	public int getCodigo_ciudad() {
		return codigo_ciudad;
	}
	public void setCodigo_ciudad(int codigo_ciudad) {
		this.codigo_ciudad = codigo_ciudad;
	}
	/*public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}*/
	
}
