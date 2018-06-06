package com.turismo.dao;

import javax.ejb.Local;

import com.turismo.entities.Destino;

@Local
public interface DestinoDAOInterfaceLocal {
	public Destino buscarPorCodigo(int codigo);
}
