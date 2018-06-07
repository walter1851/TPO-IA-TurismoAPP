package com.turismo.dao.impl;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

import com.turismo.dao.AgenciaDAOLocal;
import com.turismo.entities.Agencia;

@Stateless
@LocalBean
public class AgenciaDAO extends EntityManagerProvider implements AgenciaDAOLocal{
	public void nuevaAgencia(String nombre,String direccion,String codigo_agencia) {
		Agencia agencia = new Agencia();
		agencia.setNombre(nombre);
		agencia.setDireccion(direccion);
		agencia.setCodigo_agencia(codigo_agencia);
		getEntityManager().persist(agencia);
	}
	public void actualizarAgencia(int id,String nombre,String direccion,String codigo_agencia) {
		Agencia agencia=buscarPorId(id);
		agencia.setNombre(nombre);
		agencia.setDireccion(direccion);
		agencia.setCodigo_agencia(codigo_agencia);
		getEntityManager().merge(agencia);
	}
	public Agencia buscarPorId(int id) {
		return getEntityManager().find(Agencia.class, id);
	}
	public Agencia buscarPorCodigoAgencia(String codigo_agencia) {
		Query agenciaQuery = getEntityManager()
		.createQuery("SELECT a FROM agencias a " + "WHERE a.codigo_agencia = :codigo_agencia ");
		agenciaQuery.setParameter("codigo_agencia", "codigo_agencia");
		return (Agencia) agenciaQuery.getSingleResult();
	}
}
