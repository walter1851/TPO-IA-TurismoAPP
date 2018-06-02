package com.reservas.services;

import javax.ejb.Local;

import com.reservas.entities.Oferta;

@Local
public interface OfertaServiceLocal {
	public void guardarOfertaPaquete(Oferta oferta);
	public void guardarOfertaHotelera(Oferta oferta);
}
