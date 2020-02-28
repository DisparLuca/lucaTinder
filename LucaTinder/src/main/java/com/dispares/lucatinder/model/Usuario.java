package com.dispares.lucatinder.model;

/**
 * Clase Usuario con todas los atributos necesarios y con las anotaciones de persistencia para la base de datos
 * @author Pablo 
 */
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
	
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

	public Usuario(String nombre, int edad, int genero, String ciudad, List<String> categorias, String foto) {
		super();
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

}
