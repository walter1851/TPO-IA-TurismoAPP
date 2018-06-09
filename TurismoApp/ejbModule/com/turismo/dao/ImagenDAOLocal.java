package com.turismo.dao;

import javax.ejb.Local;

import com.turismo.entities.Establecimiento;
import com.turismo.entities.Hotel;
import com.turismo.entities.Imagen;

@Local
public interface ImagenDAOLocal {
	public void nuevaImagen(String url, Establecimiento establecimiento, Hotel hotel);
	public void actualizarImagen(int imagen_id,String url,Establecimiento establecimiento,Hotel hotel);
	public Imagen buscarPorCodigo(int codigo);
}
