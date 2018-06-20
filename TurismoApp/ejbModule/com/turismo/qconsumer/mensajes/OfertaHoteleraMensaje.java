package com.turismo.qconsumer.mensajes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OfertaHoteleraMensaje {
	@JsonProperty("id")
	private int idOfertaHotelera;
	private String nombre;
	private float precio;// precio de la habitacion
	private int cupo;
	private String mediosDePago;
	private String tipoHabitacion; // SIMPLE, DOBLE, TRIPLE
	private EstablecimientoMensaje establecimiento;
	private String fechaDesde;// Ej: 2007-04-05T12:30-02:00
	private String fechaHasta;// Ej: 2007-04-05T12:30-02:00
	private String politicas;// Texto con las politicas
	private String servicios;
	
	public int getIdOfertaHotelera() {
		return idOfertaHotelera;
	}
	public void setIdOfertaHotelera(int idOfertaHotelera) {
		this.idOfertaHotelera = idOfertaHotelera;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getCupo() {
		return cupo;
	}
	public void setCupo(int cupo) {
		this.cupo = cupo;
	}
	public String getMediosDePago() {
		return mediosDePago;
	}
	public void setMediosDePago(String mediosDePago) {
		this.mediosDePago = mediosDePago;
	}
	public String getTipoHabitacion() {
		return tipoHabitacion;
	}
	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}
	public EstablecimientoMensaje getEstablecimiento() {
		return establecimiento;
	}
	public void setEstablecimiento(EstablecimientoMensaje establecimiento) {
		this.establecimiento = establecimiento;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public String getPoliticas() {
		return politicas;
	}
	public void setPoliticas(String politicas) {
		this.politicas = politicas;
	}
	public String getServicios() {
		return servicios;
	}
	public void setServicios(String servicios) {
		this.servicios = servicios;
	}

}
