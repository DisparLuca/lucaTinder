package com.dispares.lucatinder.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**	
 * clase para realizar la codificacion de contraseñas 
 * 
 * @author jesús
 * 
 */
@Component
public class Codificador implements PasswordEncoder {

	/**	
	 * metodo para codificar
	 * 
	 * @author jesús
	 * @return contraseña codificada
	 */
	@Override
	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}

	/**	
	 * metodo para codificar
	 * 
	 * @author jesús
	 * @return true si coinciden las contraseñas
	 */
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return rawPassword.toString().equals(encodedPassword);
	}
}
