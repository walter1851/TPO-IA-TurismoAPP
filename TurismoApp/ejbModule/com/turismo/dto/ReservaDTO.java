package com.turismo.dto;

public class ReservaDTO {
	private float montoTotal;
	private String nombre;
	private String apellido;
	private String fechaCheckIn;
	private String fechaCheckOut;

	public String getFechaCheckIn() {
		return fechaCheckIn;
	}
	public void setFechaCheckIn(String fechaCheckIn) {
		this.fechaCheckIn = fechaCheckIn;
	}
	public String getFechaCheckOut() {
		return fechaCheckOut;
	}
	public void setFechaCheckOut(String fechaCheckOut) {
		this.fechaCheckOut = fechaCheckOut;
	}
	public float getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
}
