package com.reservas.services;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.reservas.entities.Establecimiento;
import com.reservas.entities.dao.EstablecimientoDAO;

@Stateless
@LocalBean
public class EstablecimientoService implements EstablecimientoServiceLocal{
	@EJB
	EstablecimientoDAO establecimientoDAO;
	
public void guardarEstablecimiento(Establecimiento establecimiento) {
	if (!existeEstablecimiento(establecimiento))
		establecimientoDAO.nuevoEstablecimiento(establecimiento);
}
private boolean existeEstablecimiento(Establecimiento establecimiento) {
	Establecimiento establecimientoFromDatabase = establecimientoDAO.buscarPorCodigo(establecimiento.getEstablecimiento_id());
	if (establecimientoFromDatabase == null)
		return false;
	else
		return true;
}
}
