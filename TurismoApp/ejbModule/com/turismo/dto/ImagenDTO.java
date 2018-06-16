package com.turismo.dto;


public class ImagenDTO {
	private int imagen_id;
	private String url;
	private EstablecimientoDTO establecimientoDTO;
	private HotelDTO hotelDTO;
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
	public EstablecimientoDTO getEstablecimientoDTO() {
		return establecimientoDTO;
	}
	public void setEstablecimientoDTO(EstablecimientoDTO establecimientoDTO) {
		this.establecimientoDTO = establecimientoDTO;
	}
	public HotelDTO getHotelDTO() {
		return hotelDTO;
	}
	public void setHotelDTO(HotelDTO hotelDTO) {
		this.hotelDTO = hotelDTO;
	}
	
}
