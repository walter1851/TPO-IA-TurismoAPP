package com.reservas.services;

import javax.ejb.Local;

import com.reservas.entities.Agencia;

@Local
public interface AgenciaServiceInterfaceLocal {
	void guardarAgencia(Agencia agencia);
}