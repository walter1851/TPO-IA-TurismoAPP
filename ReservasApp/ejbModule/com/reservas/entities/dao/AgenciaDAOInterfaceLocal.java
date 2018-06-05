package com.reservas.entities.dao;

import javax.ejb.Local;

import com.reservas.entities.Agencia;

@Local
public interface AgenciaDAOInterfaceLocal {
	public void nuevaAgencia(Agencia agencia);
	public void actualizarAgencia(Agencia agencia);
	public Agencia buscarPorCodigo(int codigo);
}
