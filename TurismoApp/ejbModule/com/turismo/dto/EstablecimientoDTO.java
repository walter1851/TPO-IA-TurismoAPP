package com.turismo.dto;

import java.util.List;

public class EstablecimientoDTO {
	private int establecimiento_id;
	private String nombre;
	private String direccion;
	private String ciudad;
	private String descripcion;
	private int estrellas;
	private String latitud;
	private String longitud;
	private String codigo_establecimiento;
	private HotelDTO hotelDTO;
	private List<String> fotosEstablecimiento;
	
	public List<String> getFotosEstablecimiento() {
		return fotosEstablecimiento;
	}
	public void setFotosEstablecimiento(List<String> fotosEstablecimiento) {
		this.fotosEstablecimiento = fotosEstablecimiento;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
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
	public String getCodigo_establecimiento() {
		return codigo_establecimiento;
	}
	public void setCodigo_establecimiento(String codigo_establecimiento) {
		this.codigo_establecimiento = codigo_establecimiento;
	}
	public HotelDTO getHotelDTO() {
		return hotelDTO;
	}
	public void setHotelDTO(HotelDTO hotelDTO) {
		this.hotelDTO = hotelDTO;
	}
	
	
}
