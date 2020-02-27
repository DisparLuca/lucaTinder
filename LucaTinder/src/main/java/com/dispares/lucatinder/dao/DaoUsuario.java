package com.dispares.lucatinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dispares.lucatinder.model.Usuario;

@Repository
public interface DaoUsuario extends JpaRepository<Usuario, Integer>{
	//comentario developmente funcionar
}
