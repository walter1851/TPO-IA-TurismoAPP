package com.turismo.rerviciosrest.response;

public class WebResponse {
	private boolean statusR;

	private Object dataObject;
	
	private String mensaje;

	public WebResponse(Boolean estado,String mensaj) {
		this.statusR = estado;
		this.mensaje = mensaj;
	}

	public WebResponse(Object data) {
		this.dataObject = data;
	}

	public boolean status() {
		return statusR;
	}

	public void setStatus(boolean status) {
		this.statusR = status;
	}

	public Object getDataObject() {
		return dataObject;
	}

	public void setDataObject(Object data) {
		this.dataObject = data;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
}
