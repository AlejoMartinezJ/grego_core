package com.grego.tracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grego.tracker.repository.IGenericRepository;
import com.grego.tracker.repository.LocationCheckpointRepository;
import com.grego.tracker.repository.model.LocationCheckpoint;
import com.grego.tracker.service.ILocationCheckpointService;

import reactor.core.publisher.Mono;

@Service
public class LocationCheckpointServiceImpl extends CrudImpl<LocationCheckpoint, Long> implements ILocationCheckpointService{

	@Autowired
	private LocationCheckpointRepository repository;
 
	@Override
	protected IGenericRepository<LocationCheckpoint, Long> getRepository() {
		// TODO Auto-generated method stub
		return repository; 
	}

	@Override
	public Mono<LocationCheckpoint> listarPorIdCheckPoint(Long checkPointId) {
		// TODO Auto-generated method stub
		return repository.findByIdCheckpoint(checkPointId);
	}

//	@Override
//	public Mono<LocationCheckpoint> listarPorIdOrder(Long orderId) {
//		// TODO Auto-generated method stub
//		return repository.findByIdOrder(orderId);
//	}
//	
}
