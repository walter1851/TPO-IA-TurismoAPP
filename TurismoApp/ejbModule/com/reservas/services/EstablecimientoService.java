package com.reservas.services;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.reservas.dao.impl.EstablecimientoDAO;
import com.reservas.dto.EstablecimientoDTO;
import com.reservas.entities.Establecimiento;
import com.reservas.services.EstablecimientoServiceInterfaceLocal;

@Stateless
@LocalBean
public class EstablecimientoService implements EstablecimientoServiceInterfaceLocal{
	@EJB
	EstablecimientoDAO establecimientoDAO;
	
public void guardarEstablecimiento(EstablecimientoDTO establecimiento) {
	//if (!existeEstablecimiento(establecimiento))
		////establecimientoDAO.nuevoEstablecimiento(establecimiento);
}
private boolean existeEstablecimiento(EstablecimientoDTO establecimiento) {
	//Establecimiento establecimientoFromDatabase = establecimientoDAO.buscarPorCodigo(establecimiento.getEstablecimiento_id());
	//if (establecimientoFromDatabase == null)
	//	return false;
	//else
		return true;
}
}
