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
			Query query = entityManager.createNativeQuery("SELECT b.id_usuarios, b.id_usuarios, b.edad, b.genero, b.ciudad, b.categorias, b.foto FROM usuarios b, usuariosLike a WHERE a.idB=b.id_usuarios AND idA=? AND a.tipo=1;");
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
			Query query = entityManager.createNativeQuery("SELECT b.id_usuarios, b.id_usuarios, b.edad, b.genero, b.ciudad, b.categorias, b.foto FROM usuarios b, usuariosLike a WHERE a.idB=b.id_usuarios AND idA=? AND a.tipo=0;");
			query.setParameter(1, id_usuario);			
			logger.info("Salgo del metodo listarDescartes");
			return query.getResultList();
		}
		
		/**
		 * Metodo que introduce n cantidad de usuarios en la base de datos
		 * @author Luca grupo 3
		 * @param numero de usuarios falsos que se van a añadir a la base de datos
		 */
		@Autowired
		DaoUsuario usuarioDao;
		public void fakeUsuario(int numeroAñadir) {
			logger.info("Se van a añadir: " + numeroAñadir + " usuarios");
			Faker faker = new Faker(new Locale("es-ES"));
			for(int i=0; i<numeroAñadir; i++) {
				
				Usuario usuario = new Usuario();
				
				usuario.setNombre(faker.name().firstName()+" " + faker.name().lastName());
				usuario.setEdad(faker.number().numberBetween(1, 100));
				usuario.setGenero(faker.number().numberBetween(0, 1));
				usuario.setCiudad(faker.address().cityName());
				usuarioDao.save(usuario);	
			}
			logger.info("Se han añadido los usuarios");
		}

	}
