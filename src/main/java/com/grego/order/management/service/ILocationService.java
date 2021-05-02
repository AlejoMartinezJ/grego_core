package com.grego.order.management.service;

import com.grego.order.management.repository.model.Location;

import reactor.core.publisher.Mono;

public interface ILocationService extends ICrud<Location, Long> {

	Mono<Location> listarPorIdOrder(Long orderId);

}
