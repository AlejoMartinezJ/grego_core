package com.grego.evidence.management.service.impl;

import com.grego.evidence.management.repository.IGenericRepository;
import com.grego.evidence.management.service.ICrud;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public abstract class CrudImpl<T, ID> implements ICrud<T, ID> {

	protected abstract IGenericRepository<T, ID> getRepository();
	
	@Override
	public Mono<T> registrar(T obj) {
		// TODO Auto-generated method stub
		return getRepository().save(obj);
	}

	@Override
	public Mono<T> modificar(T obj) {
		// TODO Auto-generated method stub
		return getRepository().save(obj);
	}

	@Override
	public Flux<T> listar() {
		// TODO Auto-generated method stub
		return getRepository().findAll();
	}

	@Override
	public Mono<T> listarPorId(ID id) {
		// TODO Auto-generated method stub
		return getRepository().findById(id);
	}

	@Override
	public Mono<Void> eliminar(ID id) {
		// TODO Auto-generated method stub
		return getRepository().deleteById(id);
	}
	
}
