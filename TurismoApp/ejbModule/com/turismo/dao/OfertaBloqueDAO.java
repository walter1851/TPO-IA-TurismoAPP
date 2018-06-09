package com.turismo.dao;

import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.turismo.entities.Oferta;
import com.turismo.entities.OfertaBloque;


@Stateless
@LocalBean
public class OfertaBloqueDAO implements OfertaBloqueDAOLocal{
	@PersistenceContext(unitName = "MyPU")
	private EntityManager entityManager;
	public void nuevoBloque(Oferta oferta, Date fecha, int cupo) {
		OfertaBloque ofertaBloque=new OfertaBloque();
		ofertaBloque.setOferta(oferta);
		ofertaBloque.setFecha_Bloque(fecha);
		ofertaBloque.setCupo(cupo);
		entityManager.merge(ofertaBloque);
	}
	public void actualizarBloque(int oferta_bloque_id, Oferta oferta, Date fecha, int cupo) {
		OfertaBloque ofertaBloque=buscarPorIdBloque(oferta_bloque_id);
		ofertaBloque.setOferta(oferta);
		ofertaBloque.setFecha_Bloque(fecha);
		ofertaBloque.setCupo(cupo);
		entityManager.merge(ofertaBloque);
	}
	public OfertaBloque buscarPorIdBloque(int oferta_bloque_id) {
		try {
		return entityManager.find(OfertaBloque.class, oferta_bloque_id);
		} catch (NoResultException nre) {
			return null;
		}
	}
}
