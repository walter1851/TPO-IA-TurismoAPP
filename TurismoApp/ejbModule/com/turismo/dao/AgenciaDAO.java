package com.turismo.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.turismo.entities.Agencia;

@Stateless
@LocalBean
public class AgenciaDAO{
	@PersistenceContext(unitName = "MyPU")
	private EntityManager entityManager;

	public void nuevaAgencia(String nombre, String direccion, String codigo_agencia) {
		Agencia agencia = new Agencia();
		agencia.setNombre(nombre);
		agencia.setDireccion(direccion);
		agencia.setCodigo_agencia(codigo_agencia);
		entityManager.persist(agencia);
	}

	public void actualizarAgencia(int id, String nombre, String direccion, String codigo_agencia) {
		Agencia agencia = buscarPorIdAgencia(id);
		agencia.setNombre(nombre);
		agencia.setDireccion(direccion);
		agencia.setCodigo_agencia(codigo_agencia);
		entityManager.merge(agencia);
	}

	public Agencia buscarPorIdAgencia(int idAgencia) {
		try {
			return entityManager.find(Agencia.class, idAgencia);
		} catch (NoResultException nre) {
			return null;
		}
	}

	public Agencia buscarPorCodigoAgencia(String codigo_agencia) {
		try {
			Query agenciaQuery = entityManager
					.createQuery("SELECT a FROM agencia a " + "WHERE a.codigo_agencia = :codigo_agencia ");
			agenciaQuery.setParameter("codigo_agencia", codigo_agencia);
			return (Agencia) agenciaQuery.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
}
