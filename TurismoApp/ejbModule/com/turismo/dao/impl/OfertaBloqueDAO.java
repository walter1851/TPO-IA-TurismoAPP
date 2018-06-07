package com.turismo.dao.impl;

import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.turismo.dao.OfertaBloqueDAOLocal;
import com.turismo.entities.Oferta;
import com.turismo.entities.OfertaBloque;


@Stateless
@LocalBean
public class OfertaBloqueDAO extends EntityManagerProvider implements OfertaBloqueDAOLocal{
	public void nuevoBloque(Oferta oferta, Date fecha, int cupo) {
		OfertaBloque ofertaBloque=new OfertaBloque();
		ofertaBloque.setOferta(oferta);
		ofertaBloque.setFecha_Bloque(fecha);
		ofertaBloque.setCupo(cupo);
		getEntityManager().merge(ofertaBloque);
	}
	public void actualizarBloque(int oferta_bloque_id, Oferta oferta, Date fecha, int cupo) {
		OfertaBloque ofertaBloque=buscarPorCodigo(oferta_bloque_id);
		ofertaBloque.setOferta(oferta);
		ofertaBloque.setFecha_Bloque(fecha);
		ofertaBloque.setCupo(cupo);
		getEntityManager().merge(ofertaBloque);
	}
	public OfertaBloque buscarPorCodigo(int codigo) {
		return getEntityManager().find(OfertaBloque.class, codigo);
	}
}
