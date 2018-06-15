package com.turismo.integraciones.qconsumer;

import java.time.LocalDate;
import java.util.List;

public class OfertaPaqueteMensaje {
	// Oferta Paquete
	private int idPaquete;
	private String nombrePaquete;
	private int idCiudadDestino;
	private String nombreCiudadDestino;
	private int cupo;
	private int cantPersonas;
	// AGENCIA
	private int idAgencia;
	private String nombreAgencia;
	private String direccionAgencia;
	private String estadoAgencia; // INACTIVO, ACTIVO
	// Oferta Paquete
	private String foto;
	private LocalDate fechaDesde; // Ej: 2007-04-05T12:30-02:00
	private LocalDate fechaHasta; // Ej: 2007-04-05T12:30-02:00
	private String estado; // INACTIVO, ACTIVO
	private float precio;
	private String descripcion;
	private String politicaCancelacion;
	private List<String> servicios;
	private List<String> mediosDePago;

	public int getIdPaquete() {
		return idPaquete;
	}

	public void setIdPaquete(int idPaquete) {
		this.idPaquete = idPaquete;
	}

	public String getNombrePaquete() {
		return nombrePaquete;
	}

	public void setNombrePaquete(String nombrePaquete) {
		this.nombrePaquete = nombrePaquete;
	}

	public int getIdCiudadDestino() {
		return idCiudadDestino;
	}

	public void setIdCiudadDestino(int idCiudadDestino) {
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

	public int getIdAgencia() {
		return idAgencia;
	}

	public void setIdAgencia(int idAgencia) {
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

	public LocalDate getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(LocalDate fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public LocalDate getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(LocalDate fechaHasta) {
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

	public List<String> getServicios() {
		return servicios;
	}

	public void setServicios(List<String> servicios) {
		this.servicios = servicios;
	}

	public List<String> getMediosDePago() {
		return mediosDePago;
	}

	public void setMediosDePago(List<String> mediosDePago) {
		this.mediosDePago = mediosDePago;
	}
}
