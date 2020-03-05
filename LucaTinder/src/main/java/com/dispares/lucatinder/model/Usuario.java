package com.dispares.lucatinder.model;

import java.io.Serializable;
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


/**
 * Controlador de usuarios con anotaciones de Spring
 * 
 * @author Equipo3 LucaTinder
 */

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{
	
	
	@Id
	@Column(name = "ID_Usuario")
	private int id;
	private String nombre;
	private int edad;
	private String genero;
	private String ciudad;
	//private List<String> categorias=null;
	private String foto;
	
	public Usuario() {
		super();
	}


	public Usuario(String nombre, int edad, String genero, String ciudad, String foto) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.genero = genero;
		this.ciudad = ciudad;
		//this.categorias = categorias;
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

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/*public List<String> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<String> categorias) {
		this.categorias = categorias;
	}
*/
	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", genero=" + genero + ", ciudad="
				+ ciudad + ", categorias=" + ", foto=" + foto + "]";
	}
	
}
