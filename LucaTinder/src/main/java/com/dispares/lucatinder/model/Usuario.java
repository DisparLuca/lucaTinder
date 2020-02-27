package com.dispares.lucatinder.model;

/**
 * Clase Usuario con todas los atributos necesarios y con las anotaciones de persistencia para la base de datos
 * @author Luca grupo 3
 */
import java.util.List;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javafaker.Faker;

@Entity
@Table(name = "usuarios")
public class Usuario {
	
	private static final Logger logger = LoggerFactory.getLogger(Usuario.class);
	
	@Id
	@Column(name = "ID_Usuario")
	private int id;
	private String nombre;
	private int edad;
	private int genero;//hombre=1 mujer= 0 y otros por definir
	private String ciudad;
	private List<String> categorias;
	private String foto;
	
	public Usuario() {
		super();
	}

	public Usuario(int id, String nombre, int edad, int genero, String ciudad, List<String> categorias, String foto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.genero = genero;
		this.ciudad = ciudad;
		this.categorias = categorias;
		this.foto = foto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getGenero() {
		return genero;
	}

	public void setGenero(int genero) {
		this.genero = genero;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public List<String> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<String> categorias) {
		this.categorias = categorias;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", genero=" + genero + ", ciudad="
				+ ciudad + ", categorias=" + categorias + ", foto=" + foto + "]";
	}
	
	/**
	 * Metodo que introduce n cantidad de usuarios en la base de datos
	 * @author Luca grupo 3
	 * @param numero de usuarios falsos que se van a añadir a la base de datos
	 */
	public void fakeUsuario(int numeroAñadir) {
		logger.info("Se van a añadir: " + numeroAñadir + " usuarios");
		Faker faker = new Faker(new Locale("es-ES"));
		
		for(int i=0; i<numeroAñadir; i++) {
			
			Usuario usuario = new Usuario();
			
			usuario.setNombre(faker.name().firstName()+" " + faker.name().lastName());
			usuario.setEdad(faker.number().numberBetween(1, 100));
			usuario.setGenero(faker.number().numberBetween(0, 1));
			usuario.setCiudad(faker.address().cityName());
			serviciosUsuario.add(usuario);	
		}
		logger.info("Se han añadido los usuarios");
	}
	

}
