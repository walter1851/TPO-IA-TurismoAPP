package com.reservas.dao.impl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reservas.dao.EstablecimientoDAOInterfaceLocal;
import com.reservas.entities.Agencia;
import com.reservas.entities.Establecimiento;
import com.reservas.entities.Hotel;

@Stateless
@LocalBean
public class EstablecimientoDAO extends EntityManagerProvider implements EstablecimientoDAOInterfaceLocal{
	public void nuevoEstablecimiento(String nombre,String direccion,String ciudad, String estado,String descripcion,String estrellas,String mapa,String codigo_establecimiento,Hotel hotel) {
		Establecimiento establecimiento=new Establecimiento();
		establecimiento.setNombre(nombre);
		establecimiento.setDireccion(direccion);
		establecimiento.setCiudad(ciudad);
		establecimiento.setEstado(estado);
		establecimiento.setDescripcion(descripcion);
		establecimiento.setEstrellas(estrellas);
		establecimiento.setMapa(mapa);
		establecimiento.setCodigo_establecimiento(codigo_establecimiento);
		establecimiento.setHotel(hotel);
		getEntityManager().persist(establecimiento);
	}
	public void actualizarEstablecimiento(Establecimiento establecimiento) {
		getEntityManager().merge(establecimiento);
	}
	public Establecimiento buscarPorCodigo(int codigo) {
		return getEntityManager().find(Establecimiento.class, codigo);
	}
}
