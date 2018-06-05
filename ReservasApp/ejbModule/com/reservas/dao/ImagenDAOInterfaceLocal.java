package com.reservas.dao;

import javax.ejb.Local;

import com.reservas.entities.Hotel;
import com.reservas.entities.Imagen;

@Local
public interface ImagenDAOInterfaceLocal {
	public void nuevaImagen(Imagen imagen);
	public void actualizarImagen(Hotel hotel);
	public Imagen buscarPorCodigo(int codigo);
}
