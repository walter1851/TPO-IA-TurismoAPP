package com.turismo.backoffice.logging;

import java.io.Serializable;
import java.util.Date;

public class LoggingMensaje implements Serializable {
	private static final long serialVersionUID = 1L;
	private String modulo = "MODULO_PORTAL_WEB";
	private int id_accion;
	private Date fecha = new Date();
	private String descripcion;

	public String getModulo() {
		return modulo;
	}

	public int getAccion() {
		return id_accion;
	}

	public void setAccion(int id_accion) {
		this.id_accion = id_accion;
	}

	public Date getFecha() {
		return fecha;
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
