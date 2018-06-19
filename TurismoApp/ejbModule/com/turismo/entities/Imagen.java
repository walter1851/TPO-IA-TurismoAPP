package com.turismo.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="imagenes") 
public class Imagen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int imagen_id;
	//cambiar nombre por imagenBase64
	private String imagenBase64;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "establecimiento_id")
	private Establecimiento establecimiento;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;
	
	public int getImagen_id() {
		return imagen_id;
	}
	public void setImagen_id(int imagen_id) {
		this.imagen_id = imagen_id;
	}
	public String getImagenBase64() {
		return imagenBase64;
	}
	public void setImagenBase64(String url) {
		this.imagenBase64 = url;
	}
	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}
	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
}
