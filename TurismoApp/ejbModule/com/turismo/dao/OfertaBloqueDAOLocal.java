package com.turismo.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import com.turismo.entities.Oferta;
import com.turismo.entities.OfertaBloque;

@Local
public interface OfertaBloqueDAOLocal {
	public boolean nuevoBloque(Oferta oferta,Date fecha,int cupo);
	public boolean actualizarBloque(OfertaBloque ofertaBloque);
	public OfertaBloque buscarPorIdBloque(int idBloque);
	public List<OfertaBloque> buscarBloques(int ofertaId,String fDesde, String fHasta, int cantPersonas,String tipoHabitacion) ;
}
