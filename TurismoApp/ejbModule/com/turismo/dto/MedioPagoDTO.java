package com.turismo.dto;

public class MedioPagoDTO {
	private Integer medio_de_pago_id;
	private String nombre;
	public Integer getMedio_de_pago_id() {
		return medio_de_pago_id;
	}
	public void setMedio_de_pago_id(int medio_de_pago_id) {
		this.medio_de_pago_id = medio_de_pago_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
