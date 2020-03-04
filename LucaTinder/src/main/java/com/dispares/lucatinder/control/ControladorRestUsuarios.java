package com.dispares.lucatinder.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dispares.lucatinder.model.Usuario;
import com.dispares.lucatinder.service.Servicios;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorRestUsuarios {
	
	@Autowired
	Servicios servUsuario;
	 
	@RequestMapping(value = "/listarLikesRest", method = RequestMethod.GET)
	public Collection<Usuario> listarLikesRest() {
		return servUsuario.getLikeados(1).stream().collect(Collectors.toList());
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
}
