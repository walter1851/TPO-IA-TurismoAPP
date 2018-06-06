package com.turismo.dto;

import java.io.InputStream;
import java.util.Date;

import com.turismo.dto.AgenciaDTO;
import com.turismo.dto.DestinoDTO;
import com.turismo.dto.EstablecimientoDTO;
import com.turismo.dto.MedioPagoDTO;
import com.turismo.dto.OfertaTipoDTO;


public class OfertaDTO {
	private int oferta_id;
	private String nombre;
	private int cupo;
	private Date fecha_desde;
	private Date fecha_hasta;
	private float precio;
	private String tipo_habitacion;
	private String politicas;
	private String servicios;
	private DestinoDTO destino;
	private InputStream foto_paquete;
	private MedioPagoDTO medioPago;
	private int cant_personas;
	private EstablecimientoDTO establecimiento;
	private AgenciaDTO agencia;
	private OfertaTipoDTO ofertaTipo;
	public int getOferta_id() {
		return oferta_id;
	}
	public void setOferta_id(int oferta_id) {
		this.oferta_id = oferta_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCupo() {
		return cupo;
	}
	public void setCupo(int cupo) {
		this.cupo = cupo;
	}
	public Date getFecha_desde() {
		return fecha_desde;
	}
	public void setFecha_desde(Date fecha_desde) {
		this.fecha_desde = fecha_desde;
	}
	public Date getFecha_hasta() {
		return fecha_hasta;
	}
	public void setFecha_hasta(Date fecha_hasta) {
		this.fecha_hasta = fecha_hasta;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public String getTipo_habitacion() {
		return tipo_habitacion;
	}
	public void setTipo_habitacion(String tipo_habitacion) {
		this.tipo_habitacion = tipo_habitacion;
	}
	public String getPoliticas() {
		return politicas;
	}
	public void setPoliticas(String politicas) {
		this.politicas = politicas;
	}
	public String getServicios() {
		return servicios;
	}
	public void setServicios(String servicios) {
		this.servicios = servicios;
	}
	public DestinoDTO getDestino() {
		return destino;
	}
	public void setDestino(DestinoDTO destino) {
		this.destino = destino;
	}
	public InputStream getFoto_paquete() {
		return foto_paquete;
	}
	public void setFoto_paquete(InputStream foto_paquete) {
		this.foto_paquete = foto_paquete;
	}
	public MedioPagoDTO getMedioPago() {
		return medioPago;
	}
	public void setMedioPago(MedioPagoDTO medioPago) {
		this.medioPago = medioPago;
	}
	public int getCant_personas() {
		return cant_personas;
	}
	public void setCant_personas(int cant_personas) {
		this.cant_personas = cant_personas;
	}
	public EstablecimientoDTO getEstablecimiento() {
		return establecimiento;
	}
	public void setEstablecimiento(EstablecimientoDTO establecimiento) {
		this.establecimiento = establecimiento;
	}
	public AgenciaDTO getAgencia() {
		return agencia;
	}
	public void setAgencia(AgenciaDTO agencia) {
		this.agencia = agencia;
	}
	public OfertaTipoDTO getOfertaTipo() {
		return ofertaTipo;
	}
	public void setOfertaTipo(OfertaTipoDTO ofertaTipo) {
		this.ofertaTipo = ofertaTipo;
	}
}
