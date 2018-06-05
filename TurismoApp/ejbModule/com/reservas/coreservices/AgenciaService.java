package com.reservas.coreservices;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.reservas.dao.AgenciaDAO;
import com.reservas.entities.Agencia;

@Stateless
@LocalBean
public class AgenciaService implements AgenciaServiceInterfaceLocal {
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
