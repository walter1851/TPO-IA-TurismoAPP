package com.turismo.dao.impl;

import javax.ejb.Local;

import com.turismo.entities.Destino;

@Local
public interface DestinoDAOLocal {
	public Destino buscarPorCodigo(int codigo);
}
