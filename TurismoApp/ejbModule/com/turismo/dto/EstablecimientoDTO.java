package com.turismo.dto;

public class EstablecimientoDTO {
	private int establecimiento_id;
	private String nombre;
	private String direccion;
	private String ciudad;
	private EstadoDTO estado;
	private String descripcion;
	private int estrellas;
	private String mapa;
	private int codigo_establecimiento;
	private HotelDTO hotelDTO;
	public int getEstablecimiento_id() {
		return establecimiento_id;
	}
	public void setEstablecimiento_id(int establecimiento_id) {
		this.establecimiento_id = establecimiento_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public EstadoDTO getEstado() {
		return estado;
	}
	public void setEstado(EstadoDTO estado) {
		this.estado = estado;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getEstrellas() {
		return estrellas;
	}
	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}
	public String getMapa() {
		return mapa;
	}
	public void setMapa(String mapa) {
		this.mapa = mapa;
	}
	public int getCodigo_establecimiento() {
		return codigo_establecimiento;
	}
	public void setCodigo_establecimiento(int codigo_establecimiento) {
		this.codigo_establecimiento = codigo_establecimiento;
	}
	public HotelDTO getHotelDTO() {
		return hotelDTO;
	}
	public void setHotelDTO(HotelDTO hotelDTO) {
		this.hotelDTO = hotelDTO;
	}
	
	
}
