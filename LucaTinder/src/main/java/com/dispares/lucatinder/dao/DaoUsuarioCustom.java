package com.dispares.lucatinder.dao;

import java.util.List;

import com.dispares.lucatinder.model.Usuario;

public interface DaoUsuarioCustom {

	List<Usuario> getBusquedaSimple();

	List<Usuario> getLikeados(int id_usuario);

}
