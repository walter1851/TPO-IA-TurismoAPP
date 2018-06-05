package com.reservas.services;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.reservas.dao.impl.EstablecimientoDAO;
import com.reservas.dto.EstablecimientoDTO;
import com.reservas.entities.Establecimiento;
import com.reservas.entities.Hotel;
import com.reservas.services.EstablecimientoServiceInterfaceLocal;

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
