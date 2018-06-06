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
	private String url;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
