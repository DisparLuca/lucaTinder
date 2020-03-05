package com.dispares.lucatinder.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author jesus
 */
	@Repository
	@Transactional(readOnly = true)
public class DaoClaveImpl implements DaoClaveCustom {

		private static final Logger logger = LoggerFactory.getLogger(DaoClaveImpl.class);

		@PersistenceContext 
		EntityManager entityManager;
		
		/**Metodo que devuelve un id dado el nick (nombre usado para loguearse) de registro (nombre usado para loguearse)
		 * @author jesus
		 * @param nick usuario (nombre usado para loguearse)
		 * @return id usuario
		*/
		@Override
		public Integer IdUsuarioLogeado(String usuario){
			
			logger.info("Entro en el metodo IdUsuarioLogeado");
			int resultado;
			Query query = entityManager.createNativeQuery("SELECT id_usuario FROM usuarioclave where nombre=? ;");
			query.setParameter(1, usuario);			
			
			try {
				resultado = (int) query.getSingleResult();
				
			} catch (Exception e) {
				// TODO: handle exception
				resultado = 0;
			}
			logger.info("Salgo del metodo IdUsuarioLogeado");
			return resultado;
		}

}
