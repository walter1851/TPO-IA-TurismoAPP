package com.turismo.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="reservas") 
public class Reserva {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reserva_id;
	private LocalDate fechaCheckIn;
	private LocalDate fechaCheckOut;
	private float montoTotal;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "oferta_id")
	private Oferta oferta;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "medio_de_pago_id")
	private MedioPago medioPago;
	private int usuario_id;
	private String nombre;
	
	public LocalDate getFechaCheckIn() {
		return fechaCheckIn;
	}
	public void setFechaCheckIn(LocalDate fechaCheckIn) {
		this.fechaCheckIn = fechaCheckIn;
	}
	public LocalDate getFechaCheckOut() {
		return fechaCheckOut;
	}
	public void setFechaCheckOut(LocalDate fechaCheckOut) {
		this.fechaCheckOut = fechaCheckOut;
	}
	private String apellido;


	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	private String email;
	private String dni;
	
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public int getReserva_id() {
		return reserva_id;
	}
	public void setReserva_id(int reserva_id) {
		this.reserva_id = reserva_id;
	}
	public Oferta getOferta() {
		return oferta;
	}
	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}
	public MedioPago getMedioPago() {
		return medioPago;
	}
	public void setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}
	public float getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(float montoTotal) {
		this.montoTotal = montoTotal;
	}
}
