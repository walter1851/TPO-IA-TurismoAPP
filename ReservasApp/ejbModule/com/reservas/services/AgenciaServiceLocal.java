package com.reservas.services;

import javax.ejb.Local;

import com.reservas.entities.Agencia;

@Local
public interface AgenciaServiceLocal {
	void guardarAgencia(Agencia agencia);
}
