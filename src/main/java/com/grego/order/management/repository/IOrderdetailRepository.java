package com.grego.order.management.repository;

import org.springframework.data.r2dbc.repository.Query;

import com.grego.order.management.repository.model.OrderDetail;

import reactor.core.publisher.Flux;

public interface IOrderdetailRepository extends IGenericRepository<OrderDetail, Long> {

	@Query("SELECT id, idOrder, idProducto, product, presentation, quantity, registryday FROM tb_orderdetail WHERE idOrder =  $1")
	Flux<OrderDetail> findByIdOrder(Long idOrder);
}
