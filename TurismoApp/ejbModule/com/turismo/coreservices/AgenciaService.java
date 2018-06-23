package com.turismo.coreservices;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.turismo.dao.AgenciaDAO;
import com.turismo.entities.Agencia;

@Stateless
@LocalBean
public class AgenciaService{
	@EJB
	private AgenciaDAO agenciaDao;

	public Agencia guardarAgencia(String nombre,String direccion,String codigo_agencia) {
		Agencia agenciaFromDatabase=buscarAgencia(codigo_agencia);
		if (agenciaFromDatabase==null) 
			return agenciaDao.nuevaAgencia(nombre,direccion,codigo_agencia);
		else
			return agenciaFromDatabase;
	}

	private Agencia buscarAgencia(String codigo_agencia) {
		return agenciaDao.buscarPorCodigoAgencia(codigo_agencia);

	}
}
