package com.grego.tracker.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grego.tracker.repository.ICheckPointTypeRepository;
import com.grego.tracker.repository.IGenericRepository;
import com.grego.tracker.repository.model.CheckPointType;
import com.grego.tracker.service.ICheckpointTypeService;

@Service
public class CheckpointTypeServiceImpl extends CrudImpl<CheckPointType, Long> implements ICheckpointTypeService {

	@Autowired
	private ICheckPointTypeRepository repository;

	@Override
	protected IGenericRepository<CheckPointType, Long> getRepository() {
		// TODO Auto-generated method stub
		return repository;
	}

}
