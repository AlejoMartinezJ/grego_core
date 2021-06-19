package com.grego.tracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grego.tracker.repository.IGenericRepository;
import com.grego.tracker.repository.IUsuarioRepository;
import com.grego.tracker.repository.model.Usuario;
import com.grego.tracker.service.IUsuarioService;

@Service
public class UsuarioServiceImpl extends CrudImpl<Usuario, Long> implements IUsuarioService{

	@Autowired
	private IUsuarioRepository repository;
 
	@Override
	protected IGenericRepository<Usuario, Long> getRepository() {
		// TODO Auto-generated method stub
		return repository; 
	}
	
}
