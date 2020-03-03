package com.dispares.lucatinder.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dispares.lucatinder.model.Usuario;
import com.github.javafaker.Faker;



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
		
		/**Metodo que devuelve una lista de todos los usuarios a los que ha dado like un id
		 * @author pablo
		 * @param id del usuario del que se quiere ver a quien ha dado like
		 * @return lista de usuarios
		*/
		@Override
		public List<Usuario> getLikeados(int id_usuario) {
			logger.info("Entro en el metodo getLikeados");
			Query query = entityManager.createNativeQuery("SELECT  b.nombre, b.id_usuario, b.edad, b.genero, b.ciudad, b.categorias, b.foto FROM usuarios b, usuariosLike a WHERE a.idB=b.id_usuario AND idA=? AND a.tipo=1;",Usuario.class);
			query.setParameter(1, id_usuario);		
			logger.info("Salgo del metodo getLikeados");
	        return query.getResultList();
		}
		
		/**Metodo que devuelve una lista de todos los usuarios a los que ha dado dislike un id
		 * @author pablo
		 * @param id del usuario del que se quiere ver a quien ha dado dislike
		 * @return lista de usuarios
		*/
		@Override
		public List<Usuario> listarDescartes(int id_usuario){
			
			logger.info("Entro en el metodo listarDescartes");
			Query query = entityManager.createNativeQuery("SELECT  b.nombre, b.id_usuario, b.edad, b.genero, b.ciudad, b.categorias, b.foto FROM usuarios b, usuariosLike a WHERE a.idB=b.id_usuario AND idA=? AND a.tipo=0;",Usuario.class);
			query.setParameter(1, id_usuario);			
			logger.info("Salgo del metodo listarDescartes");
			return query.getResultList();
		}
		
	}
