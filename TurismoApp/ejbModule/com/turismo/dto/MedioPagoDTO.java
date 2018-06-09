package com.turismo.dto;

public class MedioPagoDTO {
	private int medio_de_pago_id;
	private String nombre;
	private String codigo;
	public int getMedio_de_pago_id() {
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
