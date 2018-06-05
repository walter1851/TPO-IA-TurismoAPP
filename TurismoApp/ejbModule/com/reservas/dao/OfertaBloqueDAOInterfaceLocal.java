package com.reservas.dao;

import java.util.Date;

import javax.ejb.Local;

import com.reservas.entities.Oferta;
import com.reservas.entities.OfertaBloque;

@Local
public interface OfertaBloqueDAOInterfaceLocal {
	public void nuevoBloque(Oferta oferta,Date fecha,int cupo);
	public void actualizarBloque(int oferta_bloque_id,Oferta oferta,Date fecha,int cupo);
	public OfertaBloque buscarPorCodigo(int codigo);
}
