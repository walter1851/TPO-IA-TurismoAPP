package com.turismo.qconsumer.mensajes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CiudadMensaje {
	@JsonProperty("codigo_ciudad")
	private int codigo_ciudad;
	//SE SUPONE QUE EL NOMBRE NO ES NECESARIO
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
