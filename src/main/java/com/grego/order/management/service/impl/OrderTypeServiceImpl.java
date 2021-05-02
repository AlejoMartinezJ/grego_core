package com.grego.order.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grego.order.management.repository.IGenericRepository;
import com.grego.order.management.repository.IOrdertypeRepository;
import com.grego.order.management.repository.model.OrderType;
import com.grego.order.management.service.IOrdertypeService;

@Service
public class OrderTypeServiceImpl extends CrudImpl<OrderType, Long> implements IOrdertypeService {

	@Autowired
	private IOrdertypeRepository repository;

	@Override
	protected IGenericRepository<OrderType, Long> getRepository() {
		// TODO Auto-generated method stub
		return repository;
	}


}
