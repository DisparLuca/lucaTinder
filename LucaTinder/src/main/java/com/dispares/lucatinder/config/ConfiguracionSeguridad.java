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




@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad extends WebSecurityConfigurerAdapter {

	@Autowired
	private Codificador PasswordEncoder;

	@Autowired
	private DataSource dataSource;
	
	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
			.usersByUsernameQuery(usersQuery)
			.authoritiesByUsernameQuery(usersQuery)
			.dataSource(dataSource)
			.passwordEncoder(PasswordEncoder);
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/registroUsuario").permitAll()
			.antMatchers("/guardarUsuario").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/ver").permitAll()
			.antMatchers("/resumenUsuario").hasAuthority("1")
			.antMatchers("/modificarusuario/{id}").hasAuthority("1")
			.antMatchers("/leerUsuario").hasAuthority("1")
			.antMatchers("/eliminarUsuario").hasAuthority("1")
			.anyRequest().authenticated()
				.and()
			.csrf()
				.disable()
				.formLogin().loginPage("/login")
					.defaultSuccessUrl("/admin")
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
				.logoutSuccessUrl("/")
				.and()
			.exceptionHandling()
				.accessDeniedPage("/access-denied");
	}
	
	//Para ignorar la seguridad en estos ficheros
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
			.antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
}
