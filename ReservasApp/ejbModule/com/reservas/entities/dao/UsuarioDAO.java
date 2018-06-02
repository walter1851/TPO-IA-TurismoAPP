package com.reservas.entities.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.reservas.entities.Usuario;


@Stateless
@LocalBean
public class UsuarioDAO {
	@PersistenceContext(unitName = "MyPU")
	private EntityManager manager;
	
	public void nuevoUsuario(Usuario usuario) {
		// se persiste el objeto usuario y todo el grafo
		manager.persist(usuario);
	}
	public void actualizarUsuario(Usuario usuario) {
		manager.merge(usuario);
	}
	public Usuario buscarPorCodigo(int codigo) {
		return manager.find(Usuario.class, codigo);
	}
}
