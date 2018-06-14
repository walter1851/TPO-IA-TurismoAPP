package com.turismo.integraciones;

import java.time.LocalDate;

public class OfertaPaqueteMensaje {
private int idPaquete;
private String nombrePaquete;
private int	idCiudadDestino;
private String nombreCiudadDestino;
private int cupo;
private int cantPersonas;
//agencia: Agencia
private String foto;
private LocalDate fechaDesde; // Ej: 2007-04-05T12:30-02:00
private LocalDate fechaHasta; // Ej: 2007-04-05T12:30-02:00
private String estado; // INACTIVO, ACTIVO
private float precio;
private String descripcion;
private String politicas;
private String servicios;
private String mediosDePago;
}
