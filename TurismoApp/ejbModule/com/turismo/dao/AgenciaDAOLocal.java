package com.turismo.dao;

import javax.ejb.Local;

import com.turismo.entities.Agencia;

@Local
public interface AgenciaDAOLocal {
	public void nuevaAgencia(String nombre,String direccion,String codigo_agencia);
	public void actualizarAgencia(int id,String nombre,String direccion,String codigo_agencia);
	public Agencia buscarPorCodigoAgencia(String codigo_agencia);
	public Agencia buscarPorId(int id);
}
