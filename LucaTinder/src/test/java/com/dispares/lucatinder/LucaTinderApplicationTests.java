package com.dispares.lucatinder;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



import com.dispares.lucatinder.control.ControladorUsuarios;
import com.dispares.lucatinder.model.Usuario;
import com.dispares.lucatinder.service.Servicios;



/**	
 * Esta clase se usa para realizar test de la aplicacion
 * 
 * @author Equipo3 LucaTinder
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class LucaTinderApplicationTests {

    @Autowired
    private ControladorUsuarios  control;
    @Autowired
    private Servicios servicio;
    
    /**	
	 * test para comprobar el contexto ( true is true)
	 * 
	 * @author jesús
	 */
    @Test
	void contextLoads() throws Exception{
    	assertThat(true).isTrue();
	}
    
    /**	
	 * test para comprobar el contexto ( existe control)
	 * 
	 * @author jesús
	 */
	@Test
	void contextLoads1() throws Exception{
		assertThat(control).isNotNull();
		
	}
	
	@Test
	void usuarioCreado() throws Exception{
		
		ArrayList<String> categorias = new ArrayList<String>();
		categorias.add("test1");
		categorias.add("test1");
		Usuario usuario = new Usuario("longevina", 100, "1", "Ciudadlongeva", categorias, "fotovintage");
		servicio.salvarUsuario(usuario);
		/*servicio.recuperarUsuario(usuario.getNombre());
		assertThat(servicio.recuperarUsuario(usuario.getNombre())).isEqualTo(usuario);
		servicio.borrarUsuario();*/
	}
	
}
