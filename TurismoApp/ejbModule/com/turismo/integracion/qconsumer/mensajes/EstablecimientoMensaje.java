package com.turismo.integracion.qconsumer.mensajes;

public class EstablecimientoMensaje {
	private int id;
	private String uid; // Id recibido del backoffice
	private String nombre;
	private String direccion;
	private CiudadMensaje ciudad;
	private HotelMensaje hotel;
	private String descripcion;
	private MapaMensaje mapa;
	private String fotoestablecimiento;// Esto es una foto sola, no es un array
	private int estrellas;// de 1 a 5
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public CiudadMensaje getCiudad() {
		return ciudad;
	}
	public void setCiudad(CiudadMensaje ciudad) {
		this.ciudad = ciudad;
	}
	public HotelMensaje getHotel() {
		return hotel;
	}
	public void setHotel(HotelMensaje hotel) {
		this.hotel = hotel;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public MapaMensaje getMapa() {
		return mapa;
	}
	public void setMapa(MapaMensaje mapa) {
		this.mapa = mapa;
	}
	public String getFotoestablecimiento() {
		return fotoestablecimiento;
	}
	public void setFotoestablecimiento(String fotoestablecimiento) {
		this.fotoestablecimiento = fotoestablecimiento;
	}
	public int getEstrellas() {
		return estrellas;
	}
	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}

}
