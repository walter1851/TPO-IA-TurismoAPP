package com.reservas.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="medios_de_pago") 
public class MedioPago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int medio_de_pago_id;
	private String nombre;
	@Column(unique = true)
	private int codigo;
	public int getMedio_de_pago_id() {
		return medio_de_pago_id;
	}
	public void setMedio_de_pago_id(int medio_de_pago_id) {
		this.medio_de_pago_id = medio_de_pago_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
}
