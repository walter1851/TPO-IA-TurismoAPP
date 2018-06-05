package com.reservas.services;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.reservas.coreservices.AgenciaServiceInterfaceLocal;
import com.reservas.dao.impl.AgenciaDAO;
import com.reservas.entities.Agencia;

@Stateless
@LocalBean
public class AgenciaService implements AgenciaServiceInterfaceLocal {
	@EJB
	AgenciaDAO agenciaDao;

	/*public void guardarAgencia(AgenciaDTO agencia) {
		if (!existeAgencia(agencia))
			agenciaDao.nuevaAgencia(agencia);
	}

	private boolean existeAgencia(AgenciaDTO agencia) {
		Agencia agenciaFromDatabase = agenciaDao.buscarPorCodigo(agencia.getId());
		if (agenciaFromDatabase == null)
			return false;
		else
			return true;
	}*/
}
