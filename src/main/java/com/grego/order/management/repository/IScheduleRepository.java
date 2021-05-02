package com.grego.order.management.repository;

import org.springframework.data.r2dbc.repository.Query;

import com.grego.order.management.repository.model.Schedule;

import reactor.core.publisher.Flux;

public interface IScheduleRepository extends IGenericRepository<Schedule, Long> {

	@Query("SELECT id, deliverydate, registryday, idOrder FROM tb_schedule where  idOrder = $1 ")
	Flux<Schedule> findByIdOrder(Long orderId);

}
