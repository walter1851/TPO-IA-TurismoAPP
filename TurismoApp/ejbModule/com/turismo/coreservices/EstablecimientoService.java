package com.turismo.coreservices;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.turismo.dao.EstablecimientoDAO;
import com.turismo.entities.Establecimiento;
import com.turismo.entities.Hotel;

@Stateless
@LocalBean
public class EstablecimientoService{
	@EJB
	private EstablecimientoDAO establecimientoDAO;
	
public Establecimiento guardarEstablecimiento(String nombre,String direccion,String ciudad, String estado,String descripcion,String estrellas,String mapa,String codigo_establecimiento,Hotel hotel) {
	if (!existeEstablecimiento(codigo_establecimiento))
		return establecimientoDAO.nuevoEstablecimiento(nombre,direccion,ciudad, estado, descripcion,estrellas, mapa, codigo_establecimiento, hotel);
	else
		return null;
}
private boolean existeEstablecimiento(String codigo_establecimiento) {
	Establecimiento establecimientoFromDatabase = establecimientoDAO.buscarPorCodigoEstablecimiento(codigo_establecimiento);
	if (establecimientoFromDatabase == null)
		return false;
	else
		return true;
}
}
