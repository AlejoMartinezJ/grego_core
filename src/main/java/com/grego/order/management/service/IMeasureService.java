package com.grego.order.management.service;

import com.grego.order.management.repository.model.Measure;

import reactor.core.publisher.Mono;

public interface IMeasureService extends ICrud<Measure, Long> {

	Mono<Measure> listarPorIdOrder(Long orderId);

}
