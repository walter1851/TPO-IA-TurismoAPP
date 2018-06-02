package com.reservas.entities.dao;

import javax.ejb.Local;

import com.reservas.entities.Hotel;
import com.reservas.entities.Imagen;

@Local
public interface ImagenDAOLocal {
	public void nuevaImagen(Imagen imagen);
	public void actualizarImagen(Hotel hotel);
	public Imagen buscarPorCodigo(int codigo);
}
