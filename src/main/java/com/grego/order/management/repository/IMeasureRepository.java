package com.grego.order.management.repository;

import com.grego.order.management.repository.model.Measure;

import reactor.core.publisher.Mono;

public interface IMeasureRepository extends IGenericRepository<Measure, Long> {

	Mono<Measure> findByIdOrder(Long idOrder);
}
