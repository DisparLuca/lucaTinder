package com.dispares.lucatinder.service;

import org.springframework.stereotype.Service;

import com.dispares.lucatinder.model.Usuario;
/**
 * 
 * @author David
 * creación de interfaz de servicios
 * creación de método salvar usuario
 */
@Service
public interface Servicios {
	public void slavarUsuario(Usuario usuario);
}
