package com.dispares.lucatinder.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dispares.lucatinder.model.Usuario;
import com.dispares.lucatinder.service.Servicios;

import com.github.javafaker.Faker;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorRestUsuarios {
	
	@Autowired
	Servicios servUsuario;
	 
	@RequestMapping(value = "/listarLikesRest", method = RequestMethod.GET)
	public Collection<Usuario> listarLikesRest() {
		return servUsuario.getLikeados(1).stream().collect(Collectors.toList());
	}
	
	@RequestMapping(value = "/listarDescartesRest", method = RequestMethod.GET)
	public Collection<Usuario> listarDescartesRest() {
		return servUsuario.getDescartes(1).stream().collect(Collectors.toList());
	}
	
	@RequestMapping(value = "/listarBusquedaSimpleRest", method = RequestMethod.GET)
	public Collection<Usuario> listarBusquedaSimpleRest() {
		System.out.println(servUsuario.getBusquedaSimple().stream().collect(Collectors.toList()));
		return servUsuario.getBusquedaSimple().stream().collect(Collectors.toList());
	}
	
	 @RequestMapping(value = "/falsearUsuariosRest", method = RequestMethod.GET)
	public void falsearUsuarios() {
		 servUsuario.fakeUsuario(50);
	}
	 
	 @RequestMapping(value = "/leerUsuarioRest", method = RequestMethod.GET)
		public Stream<Usuario> leerUsuarioRest() {
		 	Usuario usuario= servUsuario.getUsuario(1).get();
		 	ArrayList<Usuario> list = new ArrayList<>();
		 	list.add(usuario);
		 	return list.stream();
		}
	 @RequestMapping(value = "/guardarUsuarioRest", method = RequestMethod.POST, consumes = "application/json")
	 	public @ResponseBody Usuario guardarUsuario(@RequestBody Usuario usuario) {
		 Faker faker = new Faker(new Locale("es-ES"));
		 int numeroImagen=0;
		 String urlBase = "https://randomuser.me/api/portraits/";
			if(usuario.getGenero().equalsIgnoreCase("mujer")) {
				numeroImagen = faker.number().numberBetween(1, 99);
				urlBase+=  "women/" +Integer.toString(numeroImagen) + ".jpg";
			}else {	
				numeroImagen = faker.number().numberBetween(1, 99);
				urlBase+=  "men/" +Integer.toString(numeroImagen) + ".jpg";
			}
			usuario.setFoto(urlBase);
			this.servUsuario.salvarUsuario(usuario);	     
			return usuario;
		}
	 
	/* @RequestMapping(value = "/darLikeRest", method = RequestMethod.POST)
	 	public String darLikeRest(Usuario usuario) {
			//this.servUsuario.salvarUsuario(usuario);	 
		 	String string="**************************************";
		 	System.out.println(string);
		 	return string;
			
		}*/
	 
	 @RequestMapping(value = "/darLikeRest")
	 	public String darLikeRest(@RequestBody Usuario usuario) {
			System.out.println(usuario);
		 	String string="**************************************";
		 	System.out.println(string);
		 	this.servUsuario.setLike(usuario.getId(), 1);
		 	return string;
			
		}
	 
	 @RequestMapping(value = "/darDisLikeRest")
	 	public String darDisLikeRest(@RequestBody Usuario usuario) {
			System.out.println(usuario);
		 	String string="**************************************";
		 	System.out.println(string);
		 	this.servUsuario.setLike(usuario.getId(), 0);
		 	return string;
			
		}
	
	
	 
}
