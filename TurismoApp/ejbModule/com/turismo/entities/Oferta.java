package com.turismo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name="ofertas") 
public class Oferta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int oferta_id;
	private int codigo_oferta;
	private String nombre;
	private int cupo;
	private LocalDate fecha_desde;
	private LocalDate fecha_hasta;
	private float precio;
	@Enumerated(EnumType.STRING)
	private TipoHabitacion tipoHabitacion;
	@Column(length=10000000)
	private String politicas;
	@Column(length=10000000)
	private String servicios;
	@ManyToOne
	@JoinColumn(name="destino_id")
	private Destino destino;
	@Column(length=10000000)
	private String descriptionPaquete;
	@Column(length=10000000)
	private String foto_paquete;
	@ManyToOne
	@JoinColumn(name="medio_de_pago_id")
	private MedioPago medioPago;
	private int cant_personas;
	@ManyToOne
	@JoinColumn(name="establecimiento_id")
	private Establecimiento establecimiento;
	@ManyToOne
	@JoinColumn(name="agencia_id")
	private Agencia agencia;
	@Enumerated(EnumType.STRING)
	OfertaTipo ofertaTipo;
	
	public int getCodigo_oferta() {
		return codigo_oferta;
	}
	public void setCodigo_oferta(int codigo_oferta) {
		this.codigo_oferta = codigo_oferta;
	}
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
	public LocalDate getFecha_desde() {
		return fecha_desde;
	}
	public void setFecha_desde(LocalDate fecha_desde) {
		this.fecha_desde = fecha_desde;
	}
	public LocalDate getFecha_hasta() {
		return fecha_hasta;
	}
	public void setFecha_hasta(LocalDate fecha_hasta) {
		this.fecha_hasta = fecha_hasta;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public TipoHabitacion getTipo_habitacion() {
		return tipoHabitacion;
	}
	public void setTipo_habitacion(TipoHabitacion tipo_habitacion) {
		this.tipoHabitacion = tipo_habitacion;
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
	public String getFoto_paquete() {
		return foto_paquete;
	}
	public void setFoto_paquete(String foto_paquete) {
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
	public String getDescriptionPaquete() {
		return descriptionPaquete;
	}
	public void setDescriptionPaquete(String descriptionPaquete) {
		this.descriptionPaquete = descriptionPaquete;
	}
}
