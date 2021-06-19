package com.grego.tracker.service;

import com.grego.tracker.entity.CheckPointAllResponse;
import com.grego.tracker.entity.CheckPointRequest;
import com.grego.tracker.entity.CheckPointResponse;
import com.grego.tracker.repository.model.CheckPoint;

import reactor.core.publisher.Mono;

public interface ICheckpointService extends ICrud<CheckPoint, Long> {

	Mono<CheckPointResponse> registrarCheckPoint(CheckPointRequest request);
	
	Mono<CheckPointAllResponse> listarCheckPoints(Long idOrder);
}
