package com.turismo.dao.impl;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.turismo.dao.AgenciaDAOLocal;
import com.turismo.entities.Agencia;

@Stateless
@LocalBean
public class AgenciaDAO implements AgenciaDAOLocal{
	@PersistenceContext(unitName = "MyPU")
	private EntityManager entityManager;
	public void nuevaAgencia(String nombre,String direccion,String codigo_agencia) {
		Agencia agencia = new Agencia();
		agencia.setNombre(nombre);
		agencia.setDireccion(direccion);
		agencia.setCodigo_agencia(codigo_agencia);
		entityManager.persist(agencia);
	}
	public void actualizarAgencia(int id,String nombre,String direccion,String codigo_agencia) {
		Agencia agencia=buscarPorId(id);
		agencia.setNombre(nombre);
		agencia.setDireccion(direccion);
		agencia.setCodigo_agencia(codigo_agencia);
		entityManager.merge(agencia);
	}
	public Agencia buscarPorId(int id) {
		return entityManager.find(Agencia.class, id);
	}
	public Agencia buscarPorCodigoAgencia(String codigo_agencia) {
		Query agenciaQuery = entityManager
		.createQuery("SELECT a FROM agencias a " + "WHERE a.codigo_agencia = :codigo_agencia ");
		agenciaQuery.setParameter("codigo_agencia", "codigo_agencia");
		return (Agencia) agenciaQuery.getSingleResult();
	}
}
