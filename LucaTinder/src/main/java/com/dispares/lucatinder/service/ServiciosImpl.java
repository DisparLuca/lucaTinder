package com.dispares.lucatinder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dispares.lucatinder.dao.DaoUsuario;
import com.dispares.lucatinder.model.Usuario;


@Service
@Transactional
public class ServiciosImpl implements Servicios{

	@Autowired
	DaoUsuario usuarioDao;
	
	
	@Override
	public void salvarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioDao.save(usuario);
	}
	
	/**Metodo que devuelve un Usuario,si lo encuentra, cuando se le proporciona un id
	 * @author pablo
	 * @param id del usuario 
	 * @return usuario del id proporcionado
	*/
	@Override
	public Optional<Usuario> getUsuario(int id) {
		return usuarioDao.findById(id);
		
	}
	
	/**Metodo que devuelve una lista de usuarios descartados por un usuario id
	 * @author pablo
	 * @param id del usuario 
	 * @return lista de usuarios descartados
	*/
	@Override
	public List<Usuario> getDescartes(int id) {
		return usuarioDao.listarDescartes(id);
	
	}
	
	
	/**Metodo para borrar un usuario prorcionando la id
	 * @author pablo
	 * @param id del usuario 
	*/
	@Override
	public void delete(int id) {
		usuarioDao.deleteById(id);
	}
	
	@Override
	public void modificarUsuario(Usuario usuario) {
		usuarioDao.save(usuario);
	}
}
