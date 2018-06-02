package com.reservas.entities.dao;

import javax.ejb.Local;

import com.reservas.entities.Usuario;

@Local
public interface UsuarioDAOLocal {
	public void nuevoUsuario(Usuario usuario);
	public void actualizarUsuario(Usuario usuario);
	public Usuario buscarPorCodigo(int codigo);
}
