package com.grego.order.management.service;

import java.util.List;

import com.grego.order.management.repository.model.Schedule;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ISchedulerService extends ICrud<Schedule, Long> {

	Flux<Schedule> listarPorIdOrder(Long orderId);

	Flux<Schedule> registrarAll(Flux<Schedule> scheduleList);

	Mono<Void> eliminarAll(List<Schedule> list);

}
