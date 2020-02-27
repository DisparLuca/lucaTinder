package com.dispares.lucatinder;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



import com.dispares.lucatinder.control.ControladorUsuarios;



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

}
