package com.turismo.dao.impl;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.turismo.entities.Agencia;
import com.turismo.entities.Destino;
import com.turismo.entities.Establecimiento;
import com.turismo.entities.MedioPago;
import com.turismo.entities.Oferta;
import com.turismo.entities.OfertaTipo;

@Stateless
@LocalBean
public class OfertaDAO implements OfertaDAOLocal {
	@PersistenceContext(unitName = "MyPU")
	private EntityManager entityManager;
	
	public void nuevaOferta(String nombre, int cupo, Date fecha_desde, Date fecha_hasta, float precio,
			String tipo_habitacion, String politicas, String servicios, Destino destino, String foto_paquete,
			MedioPago medioPago, int cant_personas, Establecimiento establecimiento, Agencia agencia,
			OfertaTipo ofertaTipo) {
		Oferta oferta=new Oferta();
		oferta.setNombre(nombre);
		oferta.setCupo(cupo);
		oferta.setFecha_desde(fecha_desde);
		oferta.setFecha_hasta(fecha_hasta);
		oferta.setPrecio(precio);
		oferta.setTipo_habitacion(tipo_habitacion);
		oferta.setPoliticas(politicas);
		oferta.setServicios(servicios);
		oferta.setDestino(destino);
		oferta.setFoto_paquete(foto_paquete);
		oferta.setMedioPago(medioPago);
		oferta.setCant_personas(cant_personas);
		oferta.setEstablecimiento(establecimiento);
		oferta.setAgencia(agencia);
		oferta.setOfertaTipo(ofertaTipo);
		entityManager.merge(oferta);
	}
	public void actualizarOferta(int oferta_id, String nombre, int cupo, Date fecha_desde, Date fecha_hasta,
			float precio, String tipo_habitacion, String politicas, String servicios, Destino destino,
			String foto_paquete, MedioPago medioPago, int cant_personas, Establecimiento establecimiento,
			Agencia agencia, OfertaTipo ofertaTipo) {
		Oferta oferta=buscarPorCodigo(oferta_id);
		oferta.setNombre(nombre);
		oferta.setCupo(cupo);
		oferta.setFecha_desde(fecha_desde);
		oferta.setFecha_hasta(fecha_hasta);
		oferta.setPrecio(precio);
		oferta.setTipo_habitacion(tipo_habitacion);
		oferta.setPoliticas(politicas);
		oferta.setServicios(servicios);
		oferta.setDestino(destino);
		oferta.setFoto_paquete(foto_paquete);
		oferta.setMedioPago(medioPago);
		oferta.setCant_personas(cant_personas);
		oferta.setEstablecimiento(establecimiento);
		oferta.setAgencia(agencia);
		oferta.setOfertaTipo(ofertaTipo);
		entityManager.merge(oferta);
		
	}
	public Oferta buscarPorCodigo(int codigo) {
		return entityManager.find(Oferta.class, codigo);
	}
	public List<Oferta> buscarOfertasHotelera(String destino, int cantPersonas, String fDesde, String fHasta) {
     	Query ofertasHotelerasQuery = entityManager.createQuery(
		"SELECT o FROM ofertas o "+
     	"INNER JOIN destinos d on d.destino_id=o.destino_id"+
     	"INNER JOIN ofertas_tipo ot on ot.oferta_id=o.oferta_id " +
		"WHERE d.destino = :destino " +
		"OR o.cantPersonas = :cantPersonas" +
		"OR o.fDesde = :fDesde" +
		"OR o.fHasta = :fHasta" +
		"OR ot.nombre = :tipoDeOferta");
     	ofertasHotelerasQuery.setParameter("destino", "destino");
     	ofertasHotelerasQuery.setParameter("cantPersonas", "cantPersonas");
     	ofertasHotelerasQuery.setParameter("fDesde", "fDesde");
     	ofertasHotelerasQuery.setParameter("fHasta", "fHasta");
     	ofertasHotelerasQuery.setParameter("tipoDeOferta", "hotelera");
	return ofertasHotelerasQuery.getResultList();
	}
	public List<Oferta> buscarOfertasPaquete(String destino, int cantPersonas, String fDesde, String fHasta) {
		Query ofertasHotelerasQuery = entityManager.createQuery(
				"SELECT o FROM ofertas o "+
		     	"INNER JOIN destinos d on d.destino_id=o.destino_id"+
		     	"INNER JOIN ofertas_tipo ot on ot.oferta_id=o.oferta_id " +
				"WHERE d.destino = :destino " +
				"OR o.cantPersonas = :cantPersonas" +
				"OR o.fDesde = :fDesde" +
				"OR o.fHasta = :fHasta" +
				"OR ot.nombre = :tipoDeOferta");
		     	ofertasHotelerasQuery.setParameter("destino", "destino");
		     	ofertasHotelerasQuery.setParameter("cantPersonas", "cantPersonas");
		     	ofertasHotelerasQuery.setParameter("fDesde", "fDesde");
		     	ofertasHotelerasQuery.setParameter("fHasta", "fHasta");
		     	ofertasHotelerasQuery.setParameter("tipoDeOferta", "paquete");
			return ofertasHotelerasQuery.getResultList();
	}

}
