package com.dispares.lucatinder.dao;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dispares.lucatinder.model.Usuario;

	@Repository
	@Transactional(readOnly = true)
	public class DaoUsuarioImpl implements DaoUsuarioCustom {
		
		private static final Logger logger = LoggerFactory.getLogger(DaoUsuarioImpl.class);

		@PersistenceContext 
		EntityManager entityManager;
		
		/**Metodo que devuelve una lista de 20 usuarios aleatorios
		 * @author alvaro
		 * @return lista de usuarios
		*/
		@Override
		public List<Usuario> getBusquedaSimple() {
			logger.info("Entro en el metodo getBusquedaSimple");
			Query query = entityManager.createNativeQuery("SELECT * FROM lucatinder.usuarios ORDER BY RAND() LIMIT 20;");
			logger.info("Salgo del metodo getBusquedaSimple");
	        return query.getResultList();
		}

	}
