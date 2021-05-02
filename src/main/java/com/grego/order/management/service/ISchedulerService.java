package com.grego.order.management.service;

import com.grego.order.management.repository.model.Schedule;

import reactor.core.publisher.Flux;

public interface ISchedulerService extends ICrud<Schedule, Long> {

	Flux<Schedule> listarPorIdOrder(Long orderId);

	Flux<Schedule> registrarAll(Flux<Schedule> scheduleList);

}
