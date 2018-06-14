package com.turismo.integraciones.qconsumer;

import java.util.Date;
import java.util.List;

public class OfertaHoteleraMensaje {
	private int id;
	private String nombreOferta;
	private float precio;// precio de la habitacion
	private int cupo; 
	private String mediosDePago;
	private String tipoHabitacion; // SIMPLE, DOBLE, TRIPLE
	//Establecimiento
	private int idEstablecimiento;
	private String uidBackOffice; // Id recibido del backoffice
	private String nombre;
	private String direccion;
	private int idCiudad;
	private String nombreCiudad;
	private int idHotel;
	private String nombreHotel;
	private String urlFotoHotel;
	private String descripcionHotel;
	private String mapaLatitud;
	private String mapaLongitud;
	private String urlFotoEstablecimiento;//Esto es una foto sola, no es un array
	private int cantEstrellas;// de 1 a 5
	//ContMensj Hot
	private Date fechaDesde;// Ej: 2007-04-05T12:30-02:00
	private Date fechaHasta;// Ej: 2007-04-05T12:30-02:00
	private List<String> politicas;// Texto con las politicas
	private String servicios;

}
