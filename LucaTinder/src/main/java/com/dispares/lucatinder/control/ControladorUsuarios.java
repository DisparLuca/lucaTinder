package com.dispares.lucatinder.control;

import java.util.Optional;

import javax.validation.Valid;

import org.apache.tomcat.jni.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dispares.lucatinder.model.Usuario;
import com.dispares.lucatinder.service.Servicios;
import com.dispares.lucatinder.service.ServiciosImpl;

/**
 * Controlador de usuarios con anotaciones de Spring
 * @author Equipo3 LucaTinder
 */
@Controller
public class ControladorUsuarios {
	
	private static final Logger logger = LoggerFactory.getLogger(ControladorUsuarios.class);
	
	@Autowired
	Servicios servUsuario;
	
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
	
	@RequestMapping(value = "/guardarUsuario", method = RequestMethod.POST)
	public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
	    this.servUsuario.salvarUsuario(usuario);	     
	    return "redirect:/";
	}
	/*
	@RequestMapping("/modificarusuario/{id}")
	public ModelAndView modificarUsuario(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("modificarusuario");
	    Optional<Usuario> usuario = this.servUsuario.getUsuario(id);
	    mav.addObject("usuario", usuario.get()); 
	    return mav;
	}*/
	
	@GetMapping("/modificarUsuario")
	public String editUser(ModelMap model) {
		logger.info("-- en EDIT");
		model.addAttribute("usuario", servUsuario.getUsuario(1).get());
		return "modificiarusuario";		
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
		servUsuario.fakeUsuario(1);
		usuario.setId(1);
		logger.info("-- en metodo leerUsuario");
		model.addAttribute("usuario",servUsuario.getUsuario(usuario.getId()).get());
		return "resumenUsuario";	
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
	@GetMapping("/eliminarUsuario")
	public ModelAndView eliminarUsuario(@RequestParam("id") int id) {
		logger.info("-- en metodo eliminarUsuario");
		servUsuario.delete(id);
		return new ModelAndView("redirect:/");		
	}
	
}
