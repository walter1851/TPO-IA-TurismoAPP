package com.reservas.coreservices;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.reservas.dao.OfertaDAO;
import com.reservas.dto.OfertaDTO;
import com.reservas.entities.Oferta;
@Stateless
@LocalBean
public class OfertaService implements OfertaServiceInterfaceLocal{
	@EJB
	OfertaDAO ofertaDAO;
	/*
public void guardarOfertaPaquete(OfertaDTO oferta) {
	if (!existeOferta(oferta))
		ofertaDAO.nuevaOferta(oferta);
} 
public void guardarOfertaHotelera(OfertaDTO oferta) {
	if (!existeOferta(oferta))
		ofertaDAO.nuevaOferta(oferta);
} 
private boolean existeOferta(OfertaDTO oferta) {
	Oferta ofertaFromDatabase = ofertaDAO.buscarPorCodigo(oferta.getOferta_id());
	if (ofertaFromDatabase == null)
		return false;
	else
		return true;
}
*/
}
