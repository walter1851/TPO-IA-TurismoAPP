package com.turismo.rest.mensajes;

public class WebResponse {
	private Object dataObject;
	private String mensaje;

	public WebResponse(Object objeto,String mensaj) {
		this.mensaje = mensaj;
		this.dataObject = objeto;
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
