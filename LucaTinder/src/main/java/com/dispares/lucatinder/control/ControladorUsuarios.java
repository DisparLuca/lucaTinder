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
	
	@GetMapping("/registroUsuarios")
	public String newUser(ModelMap model) {
		 logger.info("entra en newUser"); 
		model.addAttribute("user", new User());
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
	@PostMapping
	public String resumenUsuario(
							@Valid Usuario user,
							BindingResult result, 
							ModelMap model) {

		logger.info("entra en resumenUsuarios"); 
		if (result.hasErrors()) {
			System.out.println("--- Hay algunos errores");
			return "registroUsuario";
		}


		servUsuario.salvarUsuario(user);
		
		return "resumenUsuario";
	}
	
	@RequestMapping(value = "/guardarUsuario", method = RequestMethod.POST)
	public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
	    this.servUsuario.salvarUsuario(usuario);	     
	    return "redirect:/";
	}
	
	@RequestMapping("/modificarusuario/{id}")
	public ModelAndView modificarUsuario(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("modificar_usuario");
	    Optional<Usuario> usuario = this.servUsuario.getUsuario(id);
	    mav.addObject("usuario", usuario); 
	    return mav;
	}
	
	/**	
	 * Este metodo devuelve el usuario que se ha requerido a una id
	 * 
	 * @author Pablo
	 * 
	 * @param model
	 * @param id del usuario
	 * @return archivo web
	 */
	@GetMapping("/leerUsuario")
	public String leerUsuario(ModelMap model, @RequestParam("id") int id) {
		logger.info("-- en metodo leerUsuario");
		model.addAttribute("usuario", servUsuario.getUsuario(id));
		return "verUsuario";		
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
	
	/**	
	 * Este metodo devuelve la lista de usuarios qeu ha descartadao un usuario por su id
	 * 
	 * @author Pablo
	 * 
	 * @param model
	 * @param id del usuario
	 * @return archivo web
	 */
	@GetMapping("/listarDescartes")
	public String listarDescartes(ModelMap model, @RequestParam("id") int id) {
		logger.info("-- en metodo ListarDescartes");
		model.addAttribute("listaUsuarios", servUsuario.getDescartes(id));
		return "listausuarios";		
	}
	
}
