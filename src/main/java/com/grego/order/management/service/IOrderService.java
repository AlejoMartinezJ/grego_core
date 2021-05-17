package com.grego.order.management.service;

import com.grego.order.management.entity.OrderRequest;
import com.grego.order.management.entity.OrderResponse;
import com.grego.order.management.repository.model.Order;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IOrderService  extends ICrud<Order, Long>{

	Flux<Order> findOrderRelationsAll();
	Flux<OrderResponse> findAllOrder();
	Mono<OrderResponse> findById(Long id);
	
	Mono<OrderResponse> registrarOrder(OrderRequest request);
	Mono<OrderResponse> modificarOrder(OrderRequest request);
	
}
