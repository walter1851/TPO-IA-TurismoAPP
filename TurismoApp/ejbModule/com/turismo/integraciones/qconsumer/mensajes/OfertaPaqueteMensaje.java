package com.turismo.integraciones.qconsumer.mensajes;

public class OfertaPaqueteMensaje {
	private int id;
	private String nombre;
	private CiudadMensaje ciudadDestino;
	private int cupo;
	private int cantPersonas;
	private AgenciaMensaje agencia;
	private String foto;
	private String fechaDesde; // Ej: 2007-04-05T12:30-02:00
	private String fechaHasta; // Ej: 2007-04-05T12:30-02:00
	private String estado; // INACTIVO, ACTIVO
	private float precio;
	private String descripcion;
	private String politicas;
	private String servicios;
	private String mediosDePago;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public CiudadMensaje getCiudadDestino() {
		return ciudadDestino;
	}
	public void setCiudadDestino(CiudadMensaje ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
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
	public AgenciaMensaje getAgencia() {
		return agencia;
	}
	public void setAgencia(AgenciaMensaje agencia) {
		this.agencia = agencia;
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
	public String getMediosDePago() {
		return mediosDePago;
	}
	public void setMediosDePago(String mediosDePago) {
		this.mediosDePago = mediosDePago;
	}

}
