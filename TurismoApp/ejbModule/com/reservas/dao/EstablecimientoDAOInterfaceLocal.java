package com.reservas.dao;

import javax.ejb.Local;

import com.reservas.entities.Establecimiento;
import com.reservas.entities.Hotel;

@Local
public interface EstablecimientoDAOInterfaceLocal {
	public void nuevoEstablecimiento(String nombre,String direccion,String ciudad, String estado,String descripcion,String estrellas,String mapa,String codigo_establecimiento,Hotel Hotel);
	public void actualizarEstablecimiento(Establecimiento establecimiento);
	public Establecimiento buscarPorCodigo(int codigo);
}
