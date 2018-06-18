package com.turismo.integraciones.qconsumer.mensajes;

public class HotelMensaje {
	private int id;
	private String nombre;
	private String fotoHotel;
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
	public String getFotoHotel() {
		return fotoHotel;
	}
	public void setFotoHotel(String fotoHotel) {
		this.fotoHotel = fotoHotel;
	}

}
