package com.turismo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="establecimientos") 
public class Establecimiento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int establecimiento_id;
	private String nombre;
	private String direccion;
	private String ciudad;
	private String descripcion;
	private int estrellas;
	private String latitud;
	private String longitud;
	@Column(unique = true)
	private int codigo_establecimiento;
	@ManyToOne
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;
	
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
	public int getCodigo_establecimiento() {
		return codigo_establecimiento;
	}
	public void setCodigo_establecimiento(int codigo_establecimiento) {
		this.codigo_establecimiento = codigo_establecimiento;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
}
