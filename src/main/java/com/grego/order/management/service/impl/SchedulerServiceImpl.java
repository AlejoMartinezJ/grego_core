package com.grego.order.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grego.order.management.repository.IGenericRepository;
import com.grego.order.management.repository.IScheduleRepository;
import com.grego.order.management.repository.model.Schedule;
import com.grego.order.management.service.ISchedulerService;

import reactor.core.publisher.Flux;

@Service
public class SchedulerServiceImpl extends CrudImpl<Schedule, Long> implements ISchedulerService {

	@Autowired
	private IScheduleRepository repository;

	@Override
	protected IGenericRepository<Schedule, Long> getRepository() {
		// TODO Auto-generated method stub
		return repository;
	}

	@Override
	public Flux<Schedule> listarPorIdOrder(Long orderId) {
		// TODO Auto-generated method stub
		return repository.findByIdOrder(orderId);
	}

	@Override
	public Flux<Schedule> registrarAll(Flux<Schedule> scheduleList) {
		// TODO Auto-generated method stub
		return repository.saveAll(scheduleList);
	}

}
