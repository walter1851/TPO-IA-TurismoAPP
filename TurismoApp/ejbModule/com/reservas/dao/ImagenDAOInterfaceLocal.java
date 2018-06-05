package com.reservas.dao;

import javax.ejb.Local;

import com.reservas.entities.Establecimiento;
import com.reservas.entities.Hotel;
import com.reservas.entities.Imagen;

@Local
public interface ImagenDAOInterfaceLocal {
	public void nuevaImagen(String url, Establecimiento establecimiento, Hotel hotel);
	public void actualizarImagen(int imagen_id,String url,Establecimiento establecimiento,Hotel hotel);
	public Imagen buscarPorCodigo(int codigo);
}
