package com.dispares.lucatinder.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dispares.lucatinder.model.Usuario;


/**
 * 
 * @author David
 * creación de interfaz de servicios
 * creación de método salvar usuario
 * creación de método modificar usuario
 */
@Service
public interface Servicios {
	public void salvarUsuario(Usuario usuario);
	public void modificarUsuario(Usuario usuario);
	Optional<Usuario> getUsuario(int id);
	void delete(int id);
}
