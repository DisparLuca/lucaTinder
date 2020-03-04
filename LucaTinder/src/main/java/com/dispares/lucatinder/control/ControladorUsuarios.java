package com.dispares.lucatinder.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.apache.tomcat.jni.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.dispares.lucatinder.model.Usuario;
import com.dispares.lucatinder.service.Servicios;
import com.dispares.lucatinder.service.ServiciosImpl;

/**
 * Controlador de usuarios con anotaciones de Spring
 * @author Equipo3 LucaTinder
 */
@RestController
@Controller
public class ControladorUsuarios {
	
	private static final Logger logger = LoggerFactory.getLogger(ControladorUsuarios.class);
	
	@Autowired
	Servicios servUsuario;
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/logout")
	public void logout() {	}
	
	@GetMapping("/ver")
	public String ver(@Valid Usuario user,
			BindingResult result, 
			ModelMap model) {
		return "verUsuario";
	}
	
	@GetMapping("/registroUsuario")
	public String newUser(ModelMap model) {
		 logger.info("entra en newUser"); 
		model.addAttribute("usuario", new Usuario());
		return "registroUsuario";		
	}
	
	/**	
	 * este metodo comprueba si existen errores en el formulario y devuelve el listado de usuarios o el formulario para corregir errores
	 * 
	 * @author jesús
	 * 
	 * @param user
	 * @param result
	 * @param model
	 * @return archivo web
	 */
	@PostMapping("/resumenUsuario")
	public String resumenUsuario(
							@Valid Usuario user,
							BindingResult result, 
							ModelMap model) {

		logger.info("entra en resumenUsuarios"); 
		if (result.hasErrors()) {
			System.out.println("--- Hay algunos errores");
			return "registroUsuario";
		}

		user.setFoto("https://3.bp.blogspot.com/-DZTmmJl4faA/Wnnb5Fg46rI/AAAAAAAAACs/qyQGOEMMVh4M85dG82Tr3nytEf29AKm4wCPcBGAYYCw/s1600/beagle-adulto.jpg");
		servUsuario.salvarUsuario(user);
		
		return "resumenUsuario";
	}
	/**
	 * @author David
	 * @param model
	 * @return lista usuarios y da opción a borrarlos y modificarlos
	 */
	@RequestMapping("/listausuarios")
	public String listaUsuarios(Model model) {
	    List<Usuario> listaUsuarios = servUsuario.listarUsuarios();
	    model.addAttribute("listaUsuarios", listaUsuarios);
	    logger.info("--------listing..."); 
	    return "listausuarios";
	}
		
	@RequestMapping(value = "/guardarUsuario", method = RequestMethod.POST)
	public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
	    this.servUsuario.salvarUsuario(usuario);	     
	    return "redirect:/listausuarios";
	}
	
	/*
	@RequestMapping("/modificarusuario/{id}")
	public ModelAndView modificarUsuario(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("modificarusuario");
	    Optional<Usuario> usuario = this.servUsuario.getUsuario(id);
	    mav.addObject("usuario", usuario.get()); 
	    return mav;
	}
	*/
	@GetMapping("/modificarUsuario/{id}")
	public String modificarUsuario(@PathVariable(name = "id")  int id, ModelMap model) {
		logger.info("-- en EDIT");
		model.addAttribute("usuario", servUsuario.getUsuario(id).get());
		return "modificarusuario";		
	}
	
	/**	
	 * Este metodo devuelve el usuario que se ha requerido a una id
	 * 
	 * @author Pablo
	 * 
	 * @param model
	 * @param usuario que se va a mostrar
	 * @return archivo web
	 */
	 @RequestMapping(value = "/leerUsuario", method = RequestMethod.GET)
	public String leerUsuario(@ModelAttribute("usuario") Usuario usuario,
	         ModelMap model) {
		usuario.setId(1);
		logger.info("-- en metodo leerUsuario");
		model.addAttribute("usuario",servUsuario.getUsuario(usuario.getId()).get());
		return "resumenUsuario";	
	}
	 
	 @RequestMapping(value = "/leerUsuarioRest", method = RequestMethod.GET)
	public Usuario leerUsuarioRest() {
		servUsuario.getUsuario(1).get();
		return servUsuario.getUsuario(1).get();	
	}
	 
	 @RequestMapping(value = "/leerListaUsuariosRest", method = RequestMethod.GET)
	public Stream<Usuario> leerListaUsuariosRest() {
		return servUsuario.getLikeados(1).stream();
	}
	 
		/**	
		 * Este metodo devuelve el usuario que se ha requerido a una id
		 * 
		 * @author Pablo
		 * 
		 * @param model
		 * @param usuario que se va a mostrar
		 * @return archivo web
		 */
		 @RequestMapping(value = "/falsearUsuarios", method = RequestMethod.GET)
		public ModelAndView falsearUsuarios() {
			 logger.info("-- en metodo de falsear de ususario");
			 servUsuario.fakeUsuario(50);
			 return new ModelAndView("redirect:/");
		}
	
	/**	
	 * Este metodo elimina un usuario cuando se le proporciona una id asociada
	 * 
	 * @author Pablo
	 * @param id del usuario a eliminar
	 * @return archivo web
	 */
	@GetMapping("/eliminarUsuario/{id}")
	public ModelAndView eliminarUsuario(@PathVariable(name = "id")  int id) {
		logger.info("-- en metodo eliminarUsuario");
		servUsuario.delete(id);
		return new ModelAndView("redirect:/listausuarios");		
	}
	
	@GetMapping("/listarDescartes/{id}")
	public String listarDescartes(ModelMap model, @PathVariable(name = "id") int id) {
		logger.info("-- en metodo ListarDescartes");
		model.addAttribute("listaUsuarios", servUsuario.getDescartes(id));
		return "listausuarios";		
	}
	
	@GetMapping("/listarLikes/{id}")
	public String listarLikes(ModelMap model, @PathVariable(name = "id") int id) {
		logger.info("-- en metodo ListarLikes");
		model.addAttribute("listaUsuarios", servUsuario.getLikeados(id));
		return "listausuarios";		
	}
	
	
}
