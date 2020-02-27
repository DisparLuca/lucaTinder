package com.dispares.lucatinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dispares.lucatinder.model.Usuario;
/**
 * 
 * @author David
 * creación de DaoUsuario
 *
 */
@Repository
public interface DaoUsuario extends JpaRepository<Usuario, Integer>, CustomDaoUsuario{
	//comentario developmente funcionar
}
