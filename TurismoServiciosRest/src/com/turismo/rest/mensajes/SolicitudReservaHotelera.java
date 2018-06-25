package com.turismo.rest.mensajes;

import java.io.Serializable;

public class SolicitudReservaHotelera implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer ofertaid;
	private String fDesde;
	private String fHasta;
	private String tipoHabitacion; 
	private Integer cantTotalPersonas;
	private String nombre;
	private String apellido;
	private String dni;
	private Integer medioPagoId;
	private String mailUsuario;
	
	public Integer getOfertaid() {
		return ofertaid;
	}
	public void setOfertaid(Integer ofertaid) {
		this.ofertaid = ofertaid;
	}
	public String getfDesde() {
		return fDesde;
	}
	public void setfDesde(String fDesde) {
		this.fDesde = fDesde;
	}
	public String getfHasta() {
		return fHasta;
	}
	public void setfHasta(String fHasta) {
		this.fHasta = fHasta;
	}
	public String getTipoHabitacion() {
		return tipoHabitacion;
	}
	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}
	public Integer getCantTotalPersonas() {
		return cantTotalPersonas;
	}
	public void setCantTotalPersonas(Integer cantTotalPersonas) {
		this.cantTotalPersonas = cantTotalPersonas;
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
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Integer getMedioPagoId() {
		return medioPagoId;
	}
	public void setMedioPagoId(Integer medioPagoId) {
		this.medioPagoId = medioPagoId;
	}
	public String getMailUsuario() {
		return mailUsuario;
	}
	public void setMailUsuario(String mailUsuario) {
		this.mailUsuario = mailUsuario;
	}
	
}
