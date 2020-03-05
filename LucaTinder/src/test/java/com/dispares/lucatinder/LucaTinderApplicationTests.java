package com.dispares.lucatinder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Map;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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
	
	@Test	void usuarioCreado() throws Exception{
		Usuario usuario = new Usuario( "longevina", 100, "1", "Ciudadlongeva", "fotovintage");
		usuario.setId(10000);
		servicio.salvarUsuario(usuario);
		assertThat(servicio.getUsuario(usuario.getId()).get().getNombre()).isEqualTo(usuario.getNombre());
		servicio.delete(usuario.getId());
		}
	
	/*@Test
	public void envPostAvailable() {
		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = new TestRestTemplate().postForEntity(
				"http://localhost:" + 8080+"/login", form, Map.class);
		System.out.println("********************************************"+entity.getStatusCode());
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}*/
	
}
