package com.dispares.lucatinder.control;

import javax.validation.Valid;

import org.apache.tomcat.jni.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.dispares.lucatinder.model.Usuario;
import com.dispares.lucatinder.service.Servicios;

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
	public String resumenUsuarios(
							@Valid Usuario user,
							BindingResult result, 
							ModelMap model) {

		logger.info("entra en resumenUsuarios"); 
		if (result.hasErrors()) {
			System.out.println("--- Hay algunos errores");
			return "registroUsuario";
		}


		//servUsuario.salvarUsuario(user);
		
		return "resumenUsuarios";
	}
}
