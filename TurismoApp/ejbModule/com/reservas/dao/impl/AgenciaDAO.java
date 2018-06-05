package com.reservas.dao.impl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.reservas.dao.AgenciaDAOInterfaceLocal;
import com.reservas.entities.Agencia;

@Stateless
@LocalBean
public class AgenciaDAO extends EntityManagerProvider implements AgenciaDAOInterfaceLocal{
	public void nuevaAgencia(String nombre,String direccion,String codigo_agencia) {
		Agencia agencia = new Agencia();
		agencia.setNombre(nombre);
		agencia.setDireccion(direccion);
		agencia.setCodigo_agencia(codigo_agencia);
		getEntityManager().persist(agencia);
	}
	public void actualizarAgencia(int id,String nombre,String direccion,String codigo_agencia) {
		Agencia agencia=buscarPorCodigo(id);
		agencia.setNombre(nombre);
		agencia.setDireccion(direccion);
		agencia.setCodigo_agencia(codigo_agencia);
		getEntityManager().merge(agencia);
	}
	public Agencia buscarPorCodigo(int codigo) {
		return getEntityManager().find(Agencia.class, codigo);
	}
}
