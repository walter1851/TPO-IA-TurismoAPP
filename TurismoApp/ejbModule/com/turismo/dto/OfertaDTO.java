package com.turismo.dto;

import java.time.LocalDate;


public class OfertaDTO {
	private int oferta_id;
	private String nombre;
	private int cupo;
	private LocalDate fecha_desde;
	private LocalDate fecha_hasta;
	private float precio;
	private String tipo_habitacion;
	private String politicas;
	private String servicios;
	private DestinoDTO destinoDTO;
	private String foto_paquete;
	private String descripcionPaquete;
	public String getDescripcionPaquete() {
		return descripcionPaquete;
	}
	public void setDescripcionPaquete(String descripcionPaquete) {
		this.descripcionPaquete = descripcionPaquete;
	}
	private MedioPagoDTO medioPagoDTO;
	private int cant_personas;
	private EstablecimientoDTO establecimientoDTO;
	private AgenciaDTO agenciaDTO;
	private OfertaTipoDTO ofertaTipoDTO;
	public int getOferta_id() {
		return oferta_id;
	}
	public void setOferta_id(int oferta_id) {
		this.oferta_id = oferta_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCupo() {
		return cupo;
	}
	public void setCupo(int cupo) {
		this.cupo = cupo;
	}
	public LocalDate getFecha_desde() {
		return fecha_desde;
	}
	public void setFecha_desde(LocalDate fecha_desde) {
		this.fecha_desde = fecha_desde;
	}
	public LocalDate getFecha_hasta() {
		return fecha_hasta;
	}
	public void setFecha_hasta(LocalDate fecha_hasta) {
		this.fecha_hasta = fecha_hasta;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getTipo_habitacion() {
		return tipo_habitacion;
	}
	public void setTipo_habitacion(String tipo_habitacion) {
		this.tipo_habitacion = tipo_habitacion;
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
	public DestinoDTO getDestinoDTO() {
		return destinoDTO;
	}
	public void setDestinoDTO(DestinoDTO destinoDTO) {
		this.destinoDTO = destinoDTO;
	}
	public String getFoto_paquete() {
		return foto_paquete;
	}
	public void setFoto_paquete(String foto_paquete) {
		this.foto_paquete = foto_paquete;
	}
	public MedioPagoDTO getMedioPagoDTO() {
		return medioPagoDTO;
	}
	public void setMedioPagoDTO(MedioPagoDTO medioPagoDTO) {
		this.medioPagoDTO = medioPagoDTO;
	}
	public int getCant_personas() {
		return cant_personas;
	}
	public void setCant_personas(int cant_personas) {
		this.cant_personas = cant_personas;
	}
	public EstablecimientoDTO getEstablecimientoDTO() {
		return establecimientoDTO;
	}
	public void setEstablecimientoDTO(EstablecimientoDTO establecimientoDTO) {
		this.establecimientoDTO = establecimientoDTO;
	}
	public AgenciaDTO getAgenciaDTO() {
		return agenciaDTO;
	}
	public void setAgenciaDTO(AgenciaDTO agenciaDTO) {
		this.agenciaDTO = agenciaDTO;
	}
	public OfertaTipoDTO getOfertaTipoDTO() {
		return ofertaTipoDTO;
	}
	public void setOfertaTipoDTO(OfertaTipoDTO ofertaTipoDTO) {
		this.ofertaTipoDTO = ofertaTipoDTO;
	}
	
	
}
