package com.turismo.backoffice.logging;

import java.io.Serializable;
import java.time.LocalDate;

public class LoggingMensaje implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id_modulo = 3;//portal web
	private int id_accion;
	private String fecha= LocalDate.now().toString();
	private String descripcion;

	
	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getId_modulo() {
		return id_modulo;
	}

	public void setId_modulo(int id_modulo) {
		this.id_modulo = id_modulo;
	}

	public int getId_accion() {
		return id_accion;
	}

	public void setId_accion(int id_accion) {
		this.id_accion = id_accion;
	}

	public int getAccion() {
		return id_accion;
	}

	public void setAccion(int id_accion) {
		this.id_accion = id_accion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDescripcion() {
		return descripcion;
	}
}
