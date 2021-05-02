package com.grego.order.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grego.order.management.repository.ICompanyRepository;
import com.grego.order.management.repository.IGenericRepository;
import com.grego.order.management.repository.model.Company;
import com.grego.order.management.service.ICompanyService;

@Service
public class CompanyServiceImpl extends CrudImpl<Company, Long> implements ICompanyService {

	@Autowired
	private ICompanyRepository repository;

	@Override
	protected IGenericRepository<Company, Long> getRepository() {
		// TODO Auto-generated method stub
		return repository;
	}


}
