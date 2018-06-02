package com.reservas.services;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.reservas.entities.Agencia;
import com.reservas.entities.dao.AgenciaDAO;

@Stateless
@LocalBean
public class AgenciaService implements AgenciaServiceLocal {
	@EJB
	AgenciaDAO agenciaDao;

	public void guardarAgencia(Agencia agencia) {
		if (!existeAgencia(agencia))
			agenciaDao.nuevaAgencia(agencia);
	}

	private boolean existeAgencia(Agencia agencia) {
		Agencia agenciaFromDatabase = agenciaDao.buscarPorCodigo(agencia.getId());
		if (agenciaFromDatabase == null)
			return false;
		else
			return true;
	}
}
