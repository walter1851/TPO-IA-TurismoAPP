package com.reservas.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="oferta_bloques") 
public class OfertaBloque {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int oferta_bloque_id;
	private Oferta oferta;
	Date fecha_Bloque;
	private int cupo;
}
