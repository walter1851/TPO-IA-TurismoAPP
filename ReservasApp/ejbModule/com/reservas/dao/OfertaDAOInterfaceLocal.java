package com.reservas.dao;

import javax.ejb.Local;

import com.reservas.entities.Oferta;

@Local
public interface OfertaDAOInterfaceLocal {
	public void nuevaOferta(Oferta oferta);
	public void actualizarOferta(Oferta Oferta);
	public Oferta buscarPorCodigo(int codigo);
}
