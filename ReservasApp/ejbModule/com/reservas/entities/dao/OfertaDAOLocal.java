package com.reservas.entities.dao;

import javax.ejb.Local;

import com.reservas.entities.Oferta;

@Local
public interface OfertaDAOLocal {
	public void nuevaOferta(Oferta oferta);
	public void actualizarOferta(Oferta Oferta);
	public Oferta buscarPorCodigo(int codigo);
}
