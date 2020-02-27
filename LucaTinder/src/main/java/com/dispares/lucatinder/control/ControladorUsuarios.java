package com.dispares.lucatinder.control;

import org.apache.tomcat.jni.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador de usuarios con anotaciones de Spring
 * @author Equipo3 LucaTinder
 */
@Controller
public class ControladorUsuarios {
	
	@GetMapping("/registroUsuarios")
	public String newUser(ModelMap model) {
		model.addAttribute("user", new User());
		return "registroUsuario";		
	}
	
}
