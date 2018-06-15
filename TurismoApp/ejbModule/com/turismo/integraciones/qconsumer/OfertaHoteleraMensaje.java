package com.turismo.integraciones.qconsumer;

import java.util.Date;
import java.util.List;

public class OfertaHoteleraMensaje {
	// Campos oferta hotelera
	private int idOfertaHotelera;
	private String nombreOfertaHotelera;
	private float precio;// precio de la habitacion
	private int cupo;
	// FALTA CANT PERSONAS?
	private List<String> mediosDePago;
	private String tipoHabitacion; // SIMPLE, DOBLE, TRIPLE
	// Establecimiento
	private int idEstablecimiento;
	private String uidBackOffice; // Id recibido del backoffice
	private String nombreEstablecimiento;
	private String direccionEstablecimiento;
	private int idCiudad;
	private String nombreCiudad;
	// Hotel
	private int idHotel;
	private String nombreHotel;
	private String urlFotoHotel;
	// Establecimiento
	private String descripcionEstablecimiento;
	private String mapaLatitud;
	private String mapaLongitud;
	private String urlFotoEstablecimiento;// Esto es una foto sola, no es un array
	private int cantEstrellas;// de 1 a 5
	// Campos oferta hotelera
	private Date fechaDesde;// Ej: 2007-04-05T12:30-02:00
	private Date fechaHasta;// Ej: 2007-04-05T12:30-02:00
	private String politicaCancelacion;// Texto con las politicas
	private List<String> servicios;

	public int getIdOfertaHotelera() {
		return idOfertaHotelera;
	}

	public void setIdOfertaHotelera(int idOfertaHotelera) {
		this.idOfertaHotelera = idOfertaHotelera;
	}

	public String getNombreOfertaHotelera() {
		return nombreOfertaHotelera;
	}

	public void setNombreOfertaHotelera(String nombreOfertaHotelera) {
		this.nombreOfertaHotelera = nombreOfertaHotelera;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getCupo() {
		return cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	public List<String> getMediosDePago() {
		return mediosDePago;
	}

	public void setMediosDePago(List<String> mediosDePago) {
		this.mediosDePago = mediosDePago;
	}

	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	public int getIdEstablecimiento() {
		return idEstablecimiento;
	}

	public void setIdEstablecimiento(int idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}

	public String getUidBackOffice() {
		return uidBackOffice;
	}

	public void setUidBackOffice(String uidBackOffice) {
		this.uidBackOffice = uidBackOffice;
	}

	public String getNombreEstablecimiento() {
		return nombreEstablecimiento;
	}

	public void setNombreEstablecimiento(String nombreEstablecimiento) {
		this.nombreEstablecimiento = nombreEstablecimiento;
	}

	public String getDireccionEstablecimiento() {
		return direccionEstablecimiento;
	}

	public void setDireccionEstablecimiento(String direccionEstablecimiento) {
		this.direccionEstablecimiento = direccionEstablecimiento;
	}

	public int getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(int idCiudad) {
		this.idCiudad = idCiudad;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	public int getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(int idHotel) {
		this.idHotel = idHotel;
	}

	public String getNombreHotel() {
		return nombreHotel;
	}

	public void setNombreHotel(String nombreHotel) {
		this.nombreHotel = nombreHotel;
	}

	public String getUrlFotoHotel() {
		return urlFotoHotel;
	}

	public void setUrlFotoHotel(String urlFotoHotel) {
		this.urlFotoHotel = urlFotoHotel;
	}

	public String getDescripcionEstablecimiento() {
		return descripcionEstablecimiento;
	}

	public void setDescripcionEstablecimiento(String descripcionEstablecimiento) {
		this.descripcionEstablecimiento = descripcionEstablecimiento;
	}

	public String getMapaLatitud() {
		return mapaLatitud;
	}

	public void setMapaLatitud(String mapaLatitud) {
		this.mapaLatitud = mapaLatitud;
	}

	public String getMapaLongitud() {
		return mapaLongitud;
	}

	public void setMapaLongitud(String mapaLongitud) {
		this.mapaLongitud = mapaLongitud;
	}

	public String getUrlFotoEstablecimiento() {
		return urlFotoEstablecimiento;
	}

	public void setUrlFotoEstablecimiento(String urlFotoEstablecimiento) {
		this.urlFotoEstablecimiento = urlFotoEstablecimiento;
	}

	public int getCantEstrellas() {
		return cantEstrellas;
	}

	public void setCantEstrellas(int cantEstrellas) {
		this.cantEstrellas = cantEstrellas;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getPoliticaCancelacion() {
		return politicaCancelacion;
	}

	public void setPoliticaCancelacion(String politicaCancelacion) {
		this.politicaCancelacion = politicaCancelacion;
	}

	public List<String> getServicios() {
		return servicios;
	}

	public void setServicios(List<String> servicios) {
		this.servicios = servicios;
	}

}
