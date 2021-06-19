package com.grego.tracker.repository;

import com.grego.tracker.repository.model.LocationCheckpoint;

import reactor.core.publisher.Mono;

public interface LocationCheckpointRepository extends IGenericRepository<LocationCheckpoint, Long> {

	Mono<LocationCheckpoint> findByIdCheckpoint(Long checkPointId);

}
