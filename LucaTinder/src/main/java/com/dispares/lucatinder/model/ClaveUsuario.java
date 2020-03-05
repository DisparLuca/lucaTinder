package com.dispares.lucatinder.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**	
 * clase para la tabla usuarioclave
 * 
 * @author jesús
 * 
 */

@Entity
@Table(name = "usuarioclave")
public class ClaveUsuario implements Serializable{

	@Id
	@Column(name = "idusuarioclave")
	private int id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "clave")
	private String clave;
	@Column(name = "id_usuario")
	private int id_fk;
	@Column(name = "activo")
	private String activo;
	@Column(name = "role")
	private String role;
	
	public ClaveUsuario() {
		super();
		this.id = 0;
		this.nombre = "eee";
		this.clave = "1";
		this.id_fk = 0;
		this.activo = "1";
		this.role = "USER";
	}
	
	public ClaveUsuario(String nombre, String clave, int id_fk) {
		super();
		this.nombre = nombre;
		this.clave = clave;
		this.id_fk = id_fk;
	}
	
	public ClaveUsuario(int id, String nombre, String clave, int id_fk, String activo, String role) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.clave = clave;
		this.id_fk = id_fk;
		this.activo = activo;
		this.role = role;
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
	
	public String getClave() {
		return clave;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public int getId_fk() {
		return id_fk;
	}
	
	public void setId_fk(int id_fk) {
		this.id_fk = id_fk;
	}
	
	public String getActivo() {
		return activo;
	}
	
	public void setActivo(String activo) {
		this.activo = activo;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "ClaveUsuario [id=" + id + ", nombre=" + nombre + ", clave=" + clave + ", id_fk=" + id_fk + ", activo="
				+ activo + ", role=" + role + "]";
	}
	
	
	
}
