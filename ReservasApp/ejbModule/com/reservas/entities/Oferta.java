package com.reservas.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.io.InputStream;
import java.util.Date;

@Entity
public class Oferta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int oferta_id;
	private String nombre;
	private int cupo;
	private Date fecha_desde;
	private Date fecha_hasta;
	private float precio;
	private String tipo_habitacion;
	private String politicas;
	private String servicios;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="destino_id")
	private Destino destino;
	private InputStream foto_paquete;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="medio_de_pago_id")
	private MedioPago medioPago;
	private int cant_personas;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="establecimiento_id")
	private Establecimiento establecimiento;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id")
	private Agencia agencia;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="oferta_tipo_id")
	private OfertaTipo ofertaTipo;
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
	public Destino getDestino() {
		return destino;
	}
	public void setDestino(Destino destino) {
		this.destino = destino;
	}
	public InputStream getFoto_paquete() {
		return foto_paquete;
	}
	public void setFoto_paquete(InputStream foto_paquete) {
		this.foto_paquete = foto_paquete;
	}
	public MedioPago getMedioPago() {
		return medioPago;
	}
	public void setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}
	public int getCant_personas() {
		return cant_personas;
	}
	public void setCant_personas(int cant_personas) {
		this.cant_personas = cant_personas;
	}
	public Establecimiento getEstablecimiento() {
		return establecimiento;
	}
	public void setEstablecimiento(Establecimiento establecimiento) {
		this.establecimiento = establecimiento;
	}
	public Agencia getAgencia() {
		return agencia;
	}
	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}
	public OfertaTipo getOfertaTipo() {
		return ofertaTipo;
	}
	public void setOfertaTipo(OfertaTipo ofertaTipo) {
		this.ofertaTipo = ofertaTipo;
	}
}
