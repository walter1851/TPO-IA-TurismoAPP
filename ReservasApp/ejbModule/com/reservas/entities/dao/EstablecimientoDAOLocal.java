package com.reservas.entities.dao;

import javax.ejb.Local;

import com.reservas.entities.Establecimiento;

@Local
public interface EstablecimientoDAOLocal {
	public void nuevoEstablecimiento(Establecimiento establecimiento);
	public void actualizarEstablecimiento(Establecimiento establecimiento);
	public Establecimiento buscarPorCodigo(int codigo);
}
