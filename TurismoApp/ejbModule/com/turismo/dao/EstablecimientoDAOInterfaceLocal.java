package com.turismo.dao;

import javax.ejb.Local;

import com.turismo.entities.Establecimiento;
import com.turismo.entities.Hotel;

@Local
public interface EstablecimientoDAOInterfaceLocal {
	public void nuevoEstablecimiento(String nombre,String direccion,String ciudad, String estado,String descripcion,String estrellas,String mapa,String codigo_establecimiento,Hotel Hotel);
	public void actualizarEstablecimiento(Establecimiento establecimiento);
	public Establecimiento buscarPorCodigoEstablecimiento(String codigo_establecimiento);
}
