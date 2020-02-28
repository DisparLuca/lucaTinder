package com.dispares.lucatinder.service;

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
		//usuario.save(usuario);
	}

}
