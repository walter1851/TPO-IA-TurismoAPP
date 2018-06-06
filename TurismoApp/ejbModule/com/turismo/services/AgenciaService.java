package com.turismo.services;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.turismo.dao.impl.AgenciaDAO;
import com.turismo.entities.Agencia;
import com.turismo.services.AgenciaServiceInterfaceLocal;

@Stateless
@LocalBean
public class AgenciaService implements AgenciaServiceInterfaceLocal {
	@EJB
	AgenciaDAO agenciaDao;

	public void guardarAgencia(String nombre,String direccion,String codigo_agencia) {
		if (!existeAgencia(codigo_agencia)) {
			agenciaDao.nuevaAgencia(nombre,direccion,codigo_agencia);
		}
	}

	private boolean existeAgencia(String codigo_agencia) {
		Agencia agenciaFromDatabase = agenciaDao.buscarPorCodigoAgencia(codigo_agencia);
		if (agenciaFromDatabase == null)
			return false;
		else
			return true;
	}
}
