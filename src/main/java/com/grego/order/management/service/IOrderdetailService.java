package com.grego.order.management.service;

import java.util.List;

import com.grego.order.management.repository.model.OrderDetail;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IOrderdetailService extends ICrud<OrderDetail, Long> {

	Flux<OrderDetail> listarPorIdOrder(Long orderId);
	Flux<OrderDetail> registrarAll(Flux<OrderDetail> lista);
	Mono<Void> eliminarAll(List<OrderDetail> list);

}
