package com.turismo.dao;

import java.util.Date;

import javax.ejb.Local;

import com.turismo.entities.Oferta;
import com.turismo.entities.OfertaBloque;

@Local
public interface OfertaBloqueDAOLocal {
	public void nuevoBloque(Oferta oferta,Date fecha,int cupo);
	public void actualizarBloque(int oferta_bloque_id,Oferta oferta,Date fecha,int cupo);
	public OfertaBloque buscarPorIdBloque(int idBloque);
}
