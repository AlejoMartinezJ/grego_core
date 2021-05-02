package com.grego.order.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grego.order.management.repository.IGenericRepository;
import com.grego.order.management.repository.IUsuarioRepository;
import com.grego.order.management.repository.model.Usuario;
import com.grego.order.management.service.IUsuarioService;

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
