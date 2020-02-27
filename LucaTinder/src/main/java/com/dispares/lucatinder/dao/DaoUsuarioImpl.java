package com.dispares.lucatinder.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dispares.lucatinder.model.Usuario;

	@Repository
	@Transactional(readOnly = true)
	public class DaoUsuarioImpl implements DaoUsuarioCustom {

		@PersistenceContext 
		EntityManager entityManager;
		 
		 //Para entender un ejemplo de como se pueden hacer funciones personalizadas
		@Override
		public List<Usuario> getBusquedaSimple() {
			Query query = entityManager.createNativeQuery("SELECT * FROM lucatinder.usuarios ORDER BY RAND() LIMIT 20;");
	        return query.getResultList();
		}

	}
