package com.dispares.lucatinder.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dispares.lucatinder.dao.DaoUsuario;
import com.dispares.lucatinder.model.Usuario;


@Service
@Transactional
public class ImpleServicios implements Servicios{

	@Autowired @Qualifier("DaoUsuario")
	DaoUsuario usuarioDao;
	
	
	@Override
	public void salvarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		///usuario.save(usuario);
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

}
