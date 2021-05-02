package com.grego.order.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grego.order.management.repository.IGenericRepository;
import com.grego.order.management.repository.IOrderdetailRepository;
import com.grego.order.management.repository.model.OrderDetail;
import com.grego.order.management.service.IOrderdetailService;

import reactor.core.publisher.Flux;

@Service
public class OrderDetailServiceImpl extends CrudImpl<OrderDetail, Long> implements IOrderdetailService {

	@Autowired
	private IOrderdetailRepository repository;

	@Override
	protected IGenericRepository<OrderDetail, Long> getRepository() {
		// TODO Auto-generated method stub
		return repository;
	}

	@Override
	public Flux<OrderDetail> listarPorIdOrder(Long orderId) {
		// TODO Auto-generated method stub
		return repository.findByIdOrder(orderId);
	}

	@Override
	public Flux<OrderDetail> registrarAll(Flux<OrderDetail> lista) {
		// TODO Auto-generated method stub
		return repository.saveAll(lista);
	}

}
