package com.dispares.lucatinder.dao;

import java.util.List;

import com.dispares.lucatinder.model.Usuario;

/**
 * 
 * @author Luca grupo 3
 */
public interface DaoUsuarioCustom {

	List<Usuario> getBusquedaSimple();

	List<Usuario> getLikeados(int id_usuario);

	List<Usuario> listarDescartes(int id_usuario);

	void setLike(int idA, int idB, int like);

	int maxIdUsuario();

}
