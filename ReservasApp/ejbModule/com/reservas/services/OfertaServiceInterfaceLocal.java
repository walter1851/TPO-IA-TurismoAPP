package com.reservas.services;

import javax.ejb.Local;

import com.reservas.entities.Oferta;

@Local
public interface OfertaServiceInterfaceLocal {
	public void guardarOfertaPaquete(Oferta oferta);
	public void guardarOfertaHotelera(Oferta oferta);
}
