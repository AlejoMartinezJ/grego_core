package com.grego.order.management.repository;

import com.grego.order.management.repository.model.Location;

import reactor.core.publisher.Mono;

public interface ILocationRepository extends IGenericRepository<Location, Long> {

	Mono<Location> findByIdOrder(Long idOrder);
}
