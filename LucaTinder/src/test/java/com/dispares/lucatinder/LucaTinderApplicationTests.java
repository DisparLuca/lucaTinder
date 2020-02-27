package com.dispares.lucatinder;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



import com.dispares.lucatinder.control.ControladorUsuarios;

@RunWith(SpringRunner.class)
@SpringBootTest
class LucaTinderApplicationTests {

    @Autowired
    private ControladorUsuarios  control;
    
    
    @Test
	void contextLoads() throws Exception{
    	assertThat(true).isTrue();
	}
    
	@Test
	void contextLoads1() throws Exception{
		assertThat(control).isNotNull();
	}

}
