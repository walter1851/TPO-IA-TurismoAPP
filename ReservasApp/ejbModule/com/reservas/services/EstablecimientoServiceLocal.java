package com.reservas.services;

import javax.ejb.Local;

import com.reservas.entities.Establecimiento;

@Local
public interface EstablecimientoServiceLocal {
	public void guardarEstablecimiento(Establecimiento establecimiento);
}
