package com.turismo.qconsumer.mensajes;

public class EstablecimientoMensaje {
	private String id;
	private String uid; // Id recibido del backoffice
	private String nombre;
	private String direccion;
	//private int codigo_ciudad;
	private CiudadMensaje ciudad;
	private HotelMensaje hotel;
	private String descripcion;
	private MapaMensaje mapa;
	private String foto;// Esto es una foto sola, no es un array
	private int estrellas;// de 1 a 5

	public CiudadMensaje getCiudad() {
		return ciudad;
	}
	public void setCiudad(CiudadMensaje ciudad) {
		this.ciudad = ciudad;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	/*
	public int getCodigo_ciudad() {
		return codigo_ciudad;
	}
	public void setCodigo_ciudad(int codigo_ciudad) {
		this.codigo_ciudad = codigo_ciudad;
	}*/
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	/*public CiudadMensaje getCiudad() {
		return ciudad;
	}
	public void setCiudad(CiudadMensaje ciudad) {
		this.ciudad = ciudad;
	}*/
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
		return foto;
	}
	public void setFotoestablecimiento(String fotoEstablecimiento) {
		this.foto = fotoEstablecimiento;
	}
	public int getEstrellas() {
		return estrellas;
	}
	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}

}
