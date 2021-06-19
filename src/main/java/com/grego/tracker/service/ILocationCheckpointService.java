package com.grego.tracker.service;

import com.grego.tracker.repository.model.LocationCheckpoint;

import reactor.core.publisher.Mono;

public interface ILocationCheckpointService extends ICrud<LocationCheckpoint, Long> {

	Mono<LocationCheckpoint> listarPorIdCheckPoint(Long checkPointId);

}
