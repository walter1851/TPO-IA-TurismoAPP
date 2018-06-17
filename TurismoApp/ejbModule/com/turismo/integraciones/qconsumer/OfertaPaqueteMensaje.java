package com.turismo.integraciones.qconsumer;

import java.time.LocalDate;
import java.util.List;

public class OfertaPaqueteMensaje {
	private String idPaquete;
	private String nombrePaquete;
	private String idCiudadDestino;
	private String nombreCiudadDestino;
	private int cupo;
	private int cantPersonas;
	// AGENCIA
	private String idAgencia;
	private String nombreAgencia;
	private String direccionAgencia;
	private String estadoAgencia; // INACTIVO, ACTIVO
	// Oferta Paquete
	private String foto;
	private String fechaDesde; // Ej: 2007-04-05T12:30-02:00
	private String fechaHasta; // Ej: 2007-04-05T12:30-02:00
	private String estado; // INACTIVO, ACTIVO
	private float precio;
	private String descripcion;
	private String politicaCancelacion;
	private String servicios;
	private String mediosDePago;

	public String getIdPaquete() {
		return idPaquete;
	}

	public void setIdPaquete(String idPaquete) {
		this.idPaquete = idPaquete;
	}

	public String getNombrePaquete() {
		return nombrePaquete;
	}

	public void setNombrePaquete(String nombrePaquete) {
		this.nombrePaquete = nombrePaquete;
	}

	public String getIdCiudadDestino() {
		return idCiudadDestino;
	}

	public void setIdCiudadDestino(String idCiudadDestino) {
		this.idCiudadDestino = idCiudadDestino;
	}

	public String getNombreCiudadDestino() {
		return nombreCiudadDestino;
	}

	public void setNombreCiudadDestino(String nombreCiudadDestino) {
		this.nombreCiudadDestino = nombreCiudadDestino;
	}

	public int getCupo() {
		return cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	public int getCantPersonas() {
		return cantPersonas;
	}

	public void setCantPersonas(int cantPersonas) {
		this.cantPersonas = cantPersonas;
	}

	public String getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(String idAgencia) {
		this.idAgencia = idAgencia;
	}

	public String getNombreAgencia() {
		return nombreAgencia;
	}

	public void setNombreAgencia(String nombreAgencia) {
		this.nombreAgencia = nombreAgencia;
	}

	public String getDireccionAgencia() {
		return direccionAgencia;
	}

	public void setDireccionAgencia(String direccionAgencia) {
		this.direccionAgencia = direccionAgencia;
	}

	public String getEstadoAgencia() {
		return estadoAgencia;
	}

	public void setEstadoAgencia(String estadoAgencia) {
		this.estadoAgencia = estadoAgencia;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPoliticaCancelacion() {
		return politicaCancelacion;
	}

	public void setPoliticaCancelacion(String politicaCancelacion) {
		this.politicaCancelacion = politicaCancelacion;
	}

	public String getServicios() {
		return servicios;
	}

	public void setServicios(String servicios) {
		this.servicios = servicios;
	}

	public String getMediosDePago() {
		return mediosDePago;
	}

	public void setMediosDePago(String mediosDePago) {
		this.mediosDePago = mediosDePago;
	}
}
