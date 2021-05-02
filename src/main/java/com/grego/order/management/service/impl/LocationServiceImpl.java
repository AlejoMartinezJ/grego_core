package com.grego.order.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grego.order.management.repository.IGenericRepository;
import com.grego.order.management.repository.ILocationRepository;
import com.grego.order.management.repository.model.Location;
import com.grego.order.management.service.ILocationService;

import reactor.core.publisher.Mono;

@Service
public class LocationServiceImpl extends CrudImpl<Location, Long> implements ILocationService{

	@Autowired
	private ILocationRepository repository;
 
	@Override
	protected IGenericRepository<Location, Long> getRepository() {
		// TODO Auto-generated method stub
		return repository; 
	}

	@Override
	public Mono<Location> listarPorIdOrder(Long orderId) {
		// TODO Auto-generated method stub
		return repository.findByIdOrder(orderId);
	}
	
}
