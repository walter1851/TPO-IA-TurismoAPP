package com.turismo.integraciones.qconsumer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public class OfertaHoteleraMensaje {
	private String idOfertaHotelera;
	private String nombreOfertaHotelera;
	private float precio;// precio de la habitacion
	private int cupo;
	private String mediosDePago;
	private String tipoHabitacion; // SIMPLE, DOBLE, TRIPLE
	// Establecimiento
	private String idEstablecimiento;
	private String uidBackOffice; // Id recibido del backoffice
	private String nombreEstablecimiento;
	private String direccionEstablecimiento;
	private String idCiudad;
	private String nombreCiudad;
	// Hotel
	private String idHotel;
	private String nombreHotel;
	private String urlFotoHotel;
	// Establecimiento
	private String descripcionEstablecimiento;
	private String mapaLatitud;
	private String mapaLongitud;
	private String urlFotoEstablecimiento;// Esto es una foto sola, no es un array
	private int cantEstrellas;// de 1 a 5
	// Campos oferta hotelera
	private String fechaDesde;// Ej: 2007-04-05T12:30-02:00
	private String fechaHasta;// Ej: 2007-04-05T12:30-02:00
	private String politicaCancelacion;// Texto con las politicas
	private String servicios;

	public String getIdOfertaHotelera() {
		return idOfertaHotelera;
	}

	public void setIdOfertaHotelera(String idOfertaHotelera) {
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

	public String getMediosDePago() {
		return mediosDePago;
	}

	public void setMediosDePago(String mediosDePago) {
		this.mediosDePago = mediosDePago;
	}

	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	public void setTipoHabitacion(String tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	public String getIdEstablecimiento() {
		return idEstablecimiento;
	}

	public void setIdEstablecimiento(String idEstablecimiento) {
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

	public String getIdCiudad() {
		return idCiudad;
	}

	public void setIdCiudad(String idCiudad) {
		this.idCiudad = idCiudad;
	}

	public String getNombreCiudad() {
		return nombreCiudad;
	}

	public void setNombreCiudad(String nombreCiudad) {
		this.nombreCiudad = nombreCiudad;
	}

	public String getIdHotel() {
		return idHotel;
	}

	public void setIdHotel(String idHotel) {
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

	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getPoliticaCancelacion() {
		return politicaCancelacion;
	}

	public void setPoliticaCancelacion(String politicaCancelacion) {
		this.politicaCancelacion = politicaCancelacion;
	}

	public String getServicios() {
		return servicios;
	}

	public void setServicios(String servicios) {
		this.servicios = servicios;
	}

}
