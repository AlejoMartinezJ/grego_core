package com.grego.order.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grego.order.management.repository.IGenericRepository;
import com.grego.order.management.repository.IMeasureRepository;
import com.grego.order.management.repository.model.Measure;
import com.grego.order.management.service.IMeasureService;

import reactor.core.publisher.Mono;

@Service
public class MeasureServiceImpl extends CrudImpl<Measure, Long> implements IMeasureService {

	@Autowired
	private IMeasureRepository repository;

	@Override
	protected IGenericRepository<Measure, Long> getRepository() {
		// TODO Auto-generated method stub
		return repository;
	}

	@Override
	public Mono<Measure> listarPorIdOrder(Long orderId) {
		// TODO Auto-generated method stub
		return repository.findByIdOrder(orderId);
	}

}
