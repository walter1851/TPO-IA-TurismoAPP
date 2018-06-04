package com.reservas.bean.dto;

public class HotelDTO {
	private int hotel_id;
	private String nombre;
	private int codigo_hotel;
	public int getHotel_id() {
		return hotel_id;
	}
	public void setHotel_id(int hotel_id) {
		this.hotel_id = hotel_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCodigo_hotel() {
		return codigo_hotel;
	}
	public void setCodigo_hotel(int codigo_hotel) {
		this.codigo_hotel = codigo_hotel;
	}
	
}
