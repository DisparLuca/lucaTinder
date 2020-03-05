package com.dispares.lucatinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dispares.lucatinder.model.ClaveUsuario;
import com.dispares.lucatinder.model.Usuario;

/**
 * 
 * @author David
 *creada y recreada la interfaz
 */
public interface DaoClave extends JpaRepository<ClaveUsuario, Integer>, DaoClaveCustom {

}
