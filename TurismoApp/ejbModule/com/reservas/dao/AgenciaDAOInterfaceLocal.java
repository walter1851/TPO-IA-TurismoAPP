package com.reservas.dao;

import javax.ejb.Local;

import com.reservas.entities.Agencia;

@Local
public interface AgenciaDAOInterfaceLocal {
	public void nuevaAgencia(String nombre,String direccion,String codigo_agencia);
	public void actualizarAgencia(int id,String nombre,String direccion,String codigo_agencia);
	public Agencia buscarPorCodigo(int codigo);
}
