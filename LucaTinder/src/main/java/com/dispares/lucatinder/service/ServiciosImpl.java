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
import com.dispares.lucatinder.dao.DaoClave;
import com.dispares.lucatinder.dao.DaoClaveImpl;
import com.dispares.lucatinder.model.ClaveUsuario;
import com.dispares.lucatinder.model.Usuario;
import com.github.javafaker.Faker;


/**
 * Controlador de usuarios con anotaciones de Spring
 * 
 * @author Equipo3 LucaTinder
 */

@Service
@Transactional
public class ServiciosImpl implements Servicios{

	private static final Logger logger = LoggerFactory.getLogger(ServiciosImpl.class);
	
	@Autowired
	DaoUsuario usuarioDao;
	
	@Autowired
	DaoClave claveDao;

	
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
	
	/**	
	 * metodo para salvar clave en base de datos
	 * 
	 * @author Jesus
	 */
	@Override
	public void salvarClaveUsuario(ClaveUsuario clave) {
		// TODO Auto-generated method stub
		claveDao.save(clave);
	}
	
	/**	
	 * metodo para obtener id maxs
	 * 
	 * @author Jesus
	 */
	@Override
	public int maxIdUsuario() {
		return usuarioDao.maxIdUsuario();
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
			String urlBase = "https://randomuser.me/api/portraits/";
			int numeroImagen=0;

			int genero= faker.number().numberBetween(1, 2);
			if(genero==1) {
				usuario.setGenero("mujer");
				numeroImagen = faker.number().numberBetween(1, 100);
				urlBase+=  "women/" +Integer.toString(numeroImagen) + ".jpg";
			}else {
				usuario.setGenero("hombre");	
				numeroImagen = faker.number().numberBetween(1, 100);
				urlBase+=  "men/" +Integer.toString(numeroImagen) + ".jpg";
			}
			usuario.setCiudad(faker.address().cityName());
			usuario.setId(i);
			//usuario.setFoto("https://3.bp.blogspot.com/-DZTmmJl4faA/Wnnb5Fg46rI/AAAAAAAAACs/qyQGOEMMVh4M85dG82Tr3nytEf29AKm4wCPcBGAYYCw/s1600/beagle-adulto.jpg");
			usuario.setFoto(urlBase);
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
			
			idUsuarioLogeado = (Integer) claveDao.IdUsuarioLogeado(user.getUsername());
		}
		return idUsuarioLogeado;
	}
	
	@Override
	public List<Usuario> getDescartes(int id) {
		
		return usuarioDao.listarDescartes(id);
	}

	@Override
	public List<Usuario> getLikeados(int id) {
		return usuarioDao.getLikeados(id);
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
