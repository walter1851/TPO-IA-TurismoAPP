package com.reservas.dao.impl;

import java.io.InputStream;
import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.reservas.dao.OfertaDAOInterfaceLocal;
import com.reservas.entities.Agencia;
import com.reservas.entities.Destino;
import com.reservas.entities.Establecimiento;
import com.reservas.entities.MedioPago;
import com.reservas.entities.Oferta;
import com.reservas.entities.OfertaTipo;

@Stateless
@LocalBean
public class OfertaDAO extends EntityManagerProvider implements OfertaDAOInterfaceLocal {
	public void nuevaOferta(String nombre, int cupo, Date fecha_desde, Date fecha_hasta, float precio,
			String tipo_habitacion, String politicas, String servicios, Destino destino, InputStream foto_paquete,
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
		getEntityManager().merge(oferta);
	}
	public void actualizarOferta(int oferta_id, String nombre, int cupo, Date fecha_desde, Date fecha_hasta,
			float precio, String tipo_habitacion, String politicas, String servicios, Destino destino,
			InputStream foto_paquete, MedioPago medioPago, int cant_personas, Establecimiento establecimiento,
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
		getEntityManager().merge(oferta);
		
	}
	public Oferta buscarPorCodigo(int codigo) {
		return getEntityManager().find(Oferta.class, codigo);
	}

}
