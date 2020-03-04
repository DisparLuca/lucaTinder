package com.dispares.lucatinder.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.dispares.lucatinder.security.Codificador;


/**	
 * clase para realizar el registro de usuarios 
 * 
 * @author jesús
 * 
 */

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {

	@Autowired
	private Codificador PasswordEncoder;

	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.users-rol-query}")
	private String usersRolQuery;
	
	/**	
	 * este metodo configura la autentificacion
	 * 
	 * @author jesús
	 * 
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
			.usersByUsernameQuery(usersQuery)
			.authoritiesByUsernameQuery(usersRolQuery)
			.dataSource(dataSource)
			.passwordEncoder(PasswordEncoder);
	}
	
	
	/**	
	 * este metodo configura el protocolo http
	 * 
	 * @author jesús
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/registroUsuario").permitAll()
			.antMatchers("/guardarUsuario").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/ver").hasAuthority("USER")
			.antMatchers("/resumenUsuario").hasAuthority("USER")
			.antMatchers("/modificarusuario/{id}").hasAuthority("USER")
			.antMatchers("/leerUsuario").hasAuthority("USER")
			.antMatchers("/eliminarUsuario").hasAuthority("USER")
			.antMatchers("/darLike/**").hasAuthority("USER")
			.anyRequest().authenticated()
				.and()
			.csrf()
				.disable()
				.formLogin().loginPage("/login")
					.defaultSuccessUrl("/ver")
					.failureUrl("/login?error=true")
					.usernameParameter("nombre")
					.passwordParameter("clave")
				.and()
			.csrf()
				.disable()
				.headers().frameOptions().disable()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login")
				.and()
			.exceptionHandling()
				.accessDeniedPage("/access-denied");
	}
	
	/**	
	 * este metodo configura la seguridad de la web
	 * 
	 * @author jesús
	 * 
	 */
	//Para ignorar la seguridad en estos ficheros
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
			.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
}
