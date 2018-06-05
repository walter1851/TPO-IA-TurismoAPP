package com.reservas.dao;

import javax.ejb.Local;

import com.reservas.entities.OfertaBloque;

@Local
public interface OfertaBloqueDAOInterfaceLocal {
	public void nuevoBloque(OfertaBloque ofertaBloque);
	public void actualizarBloque(OfertaBloque ofertaBloque);
	public OfertaBloque buscarPorCodigo(int codigo);
}
