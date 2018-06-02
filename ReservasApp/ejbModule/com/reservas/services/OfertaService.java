package com.reservas.services;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.reservas.entities.Oferta;
import com.reservas.entities.dao.OfertaDAO;
@Stateless
@LocalBean
public class OfertaService implements OfertaServiceLocal{
	@EJB
	OfertaDAO ofertaDAO;
public void guardarOfertaPaquete(Oferta oferta) {
	if (!existeOferta(oferta))
		ofertaDAO.nuevaOferta(oferta);
} 
public void guardarOfertaHotelera(Oferta oferta) {
	if (!existeOferta(oferta))
		ofertaDAO.nuevaOferta(oferta);
} 
private boolean existeOferta(Oferta oferta) {
	Oferta ofertaFromDatabase = ofertaDAO.buscarPorCodigo(oferta.getOferta_id());
	if (ofertaFromDatabase == null)
		return false;
	else
		return true;
}
}
