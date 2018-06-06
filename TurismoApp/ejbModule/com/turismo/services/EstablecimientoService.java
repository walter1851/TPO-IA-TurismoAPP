package com.turismo.services;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.turismo.dao.impl.EstablecimientoDAO;
import com.turismo.dto.EstablecimientoDTO;
import com.turismo.entities.Establecimiento;
import com.turismo.entities.Hotel;
import com.turismo.services.EstablecimientoServiceInterfaceLocal;

@Stateless
@LocalBean
public class EstablecimientoService implements EstablecimientoServiceInterfaceLocal{
	@EJB
	EstablecimientoDAO establecimientoDAO;
	
public void guardarEstablecimiento(String nombre,String direccion,String ciudad, String estado,String descripcion,String estrellas,String mapa,String codigo_establecimiento,Hotel hotel) {
	if (!existeEstablecimiento(codigo_establecimiento))
		establecimientoDAO.nuevoEstablecimiento(nombre,direccion,ciudad, estado, descripcion,estrellas, mapa, codigo_establecimiento,hotel);
}
private boolean existeEstablecimiento(String codigo_establecimiento) {
	Establecimiento establecimientoFromDatabase = establecimientoDAO.buscarPorCodigoEstablecimiento(codigo_establecimiento);
	if (establecimientoFromDatabase == null)
		return false;
	else
		return true;
}
}
