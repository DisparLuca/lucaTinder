package com.dispares.lucatinder.service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dispares.lucatinder.dao.DaoUsuario;
import com.dispares.lucatinder.dao.DaoUsuarioImpl;
import com.dispares.lucatinder.model.Usuario;
import com.github.javafaker.Faker;


@Service
@Transactional
public class ServiciosImpl implements Servicios{

	private static final Logger logger = LoggerFactory.getLogger(ServiciosImpl.class);
	
	@Autowired
	DaoUsuario usuarioDao;

	/**	
	 * metodo para salvar usuario en base de datos
	 * 
	 * @author david
	 */
	@Override
	public void salvarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioDao.save(usuario);
	}
	
	/**Metodo que devuelve un Usuario,si lo encuentra, cuando se le proporciona un id
	 * @author pablo
	 * @param id del usuario 
	 * @return usuario del id proporcionado
	*/
	@Override
	public Optional<Usuario> getUsuario(int id) {
		return usuarioDao.findById(id);
		
	}
	
	/**Metodo para borrar un usuario prorcionando la id
	 * @author pablo
	 * @param id del usuario 
	*/
	@Override
	public void delete(int id) {
		usuarioDao.deleteById(id);
	}
	
	/**	
	 * metodo para modificar un usuario en base de datos
	 * 
	 * @author david
	 */
	@Override
	public void modificarUsuario(Usuario usuario) {
		usuarioDao.save(usuario);
	}
	/**
	 * @author David
	 * @param model
	 * @return metodo que devuelve la lista de todos los usuarios
	 */
	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioDao.findAll();
	}
	/**
	 * Metodo que introduce n cantidad de usuarios en la base de datos
	 * @author Luca grupo 3
	 * @param numero de usuarios falsos que se van a añadir a la base de datos
	 */
	@Override
	public void fakeUsuario(int numeroAñadir) {
		logger.info("Se van a añadir: " + numeroAñadir + " usuarios");
		Faker faker = new Faker(new Locale("es-ES"));
		for(int i=1; i<=numeroAñadir; i++) {
			
			Usuario usuario = new Usuario();
			
			usuario.setNombre(faker.name().firstName()+" " + faker.name().lastName());
			usuario.setEdad(faker.number().numberBetween(1, 100));
			int genero= (int)Math.floor(Math.random());
			if(genero<0.5) {
				usuario.setGenero("mujer");
			}else {
				usuario.setGenero("hombre");	
			}
			usuario.setCiudad(faker.address().cityName());
			usuario.setId(i);
			//usuarioDao.save(usuario);
			salvarUsuario(usuario);
						
		}
		logger.info("Se han añadido los usuarios");
	}

	/**	
	 * metodo para obtener el id de usuario logueado
	 * 
	 * @author jesús
	 * @return id de usuario
	 */
	@Override
	public Integer getIdUsuarioLogeado(){
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Integer idUsuarioLogeado = null;
		
		if(!(auth instanceof AnonymousAuthenticationToken)) {
			user = (UserDetails) auth.getPrincipal();
			
			idUsuarioLogeado = (Integer) usuarioDao.IdUsuarioLogeado(user.getUsername());
		}
		return idUsuarioLogeado;
	}
	
	/**	
	 * metodo para obtener lista de todos los usuarios a los que ha dado like
	 * 
	 * @author jesús
	 * @return id de usuario
	 */
	@Override
	public  List<Usuario> getListaLike(){
		return usuarioDao.getLikeados(getIdUsuarioLogeado());
	}
	
	/**	
	 * metodo para obtener dar like
	 * 
	 * @author jesús
	 * 
	 */
	@Override
	public  void setLike(int idB, int like){
		usuarioDao.setLike(getIdUsuarioLogeado(), idB, like);
	}
	
}
