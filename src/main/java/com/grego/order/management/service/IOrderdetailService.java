package com.grego.order.management.service;

import com.grego.order.management.repository.model.OrderDetail;

import reactor.core.publisher.Flux;

public interface IOrderdetailService extends ICrud<OrderDetail, Long> {

	Flux<OrderDetail> listarPorIdOrder(Long orderId);
	Flux<OrderDetail> registrarAll(Flux<OrderDetail> lista);

}
