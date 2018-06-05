package com.reservas.entities.dao;

import javax.ejb.Local;

import com.reservas.entities.Destino;

@Local
public interface DestinoDAOInterfaceLocal {
	public Destino buscarPorCodigo(int codigo);
}
