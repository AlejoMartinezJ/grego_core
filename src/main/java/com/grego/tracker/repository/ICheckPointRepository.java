package com.grego.tracker.repository;

import org.springframework.data.r2dbc.repository.Query;

import com.grego.tracker.repository.model.CheckPoint;

import reactor.core.publisher.Flux;

public interface ICheckPointRepository extends IGenericRepository<CheckPoint, Long> {

	@Query("SELECT id,idcheckpointtype, checkpointname,checkpointcomment ,idorder, idcheckpointuserid , checkpointtimestamp FROM tb_checkpoint where  idOrder = $1 ")
	Flux<CheckPoint>  findAllByIdOrder(Long idOrder);
}
