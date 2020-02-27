package com.dispares.lucatinder.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaoUsuario extends JpaRepository{
	
	public void salvarUsuario();
}
