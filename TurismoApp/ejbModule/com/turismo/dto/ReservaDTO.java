package com.turismo.dto;

import java.time.LocalDate;

public class ReservaDTO {
	private float montoTotal;
	private String nombre;
	private String apellido;
	private LocalDate fechaCheckIn;
	private LocalDate fechaCheckOut;

	public LocalDate getFechaCheckIn() {
		return fechaCheckIn;
	}
	public void setFechaCheckIn(LocalDate fechaCheckIn) {
		this.fechaCheckIn = fechaCheckIn;
	}
	public LocalDate getFechaCheckOut() {
		return fechaCheckOut;
	}
	public void setFechaCheckOut(LocalDate fechaCheckOut) {
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
