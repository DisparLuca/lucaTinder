package com.dispares.lucatinder.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.dispares.lucatinder.model.Usuario;
import com.dispares.lucatinder.service.Servicios;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ControladorRestUsuarios {
	
	@Autowired
	Servicios servUsuario;
	
	@RequestMapping(value = "/loginRest", method = RequestMethod.GET)
	public String login(HttpServletResponse httpServletResponse) {
		httpServletResponse.setHeader("Location", "http://localhost:8080");
		httpServletResponse.setStatus(302);
		return "redirect:/login";
		
	}
	
	
	@RequestMapping(value = "/listarLikesRest", method = RequestMethod.GET)
	public Collection<Usuario> listarLikesRest() {
		return servUsuario.getLikeados(servUsuario.getIdUsuarioLogeado()).stream().collect(Collectors.toList());
	}
	
	
	 @RequestMapping(value = "/falsearUsuariosRest", method = RequestMethod.GET)
	public void falsearUsuarios() {
		 servUsuario.fakeUsuario(50);
	}
	 
	 
	 @RequestMapping(value = "/leerUsuarioRest", method = RequestMethod.GET)
		public Stream<Usuario> leerUsuarioRest() {
		 	Usuario usuario= servUsuario.getUsuario(servUsuario.getIdUsuarioLogeado()).get();
		 	ArrayList<Usuario> list = new ArrayList<>();
		 	list.add(usuario);
		 	return list.stream();
		}
	
	 
	@RequestMapping(value = "/falsearUsuariosREST", method = RequestMethod.POST)
		public String falsearUsuarios(HttpServletResponse httpServletResponse) {
			 servUsuario.fakeUsuario(50);
			 httpServletResponse.setHeader("Location", "http://localhost:4200");
			 httpServletResponse.setStatus(302);
			 return "redirect:/usuario";
		}
	 
		 /**
		 * metodo para guardar usuario
		 *
		 * @author David grupo 3
		 * @param usuario
		 * @return String
		 */
		@RequestMapping(value = "/guardarUsuarioRest", method = RequestMethod.POST, consumes = "application/json")
		public @ResponseBody Usuario guardarUsuario(@RequestBody Usuario usuario) {
		    this.servUsuario.salvarUsuario(usuario);
		    return usuario;
		}
		
		 
}
