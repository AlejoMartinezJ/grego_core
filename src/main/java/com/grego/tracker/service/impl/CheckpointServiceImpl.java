package com.grego.tracker.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grego.tracker.entity.CheckPointAllResponse;
import com.grego.tracker.entity.CheckPointRequest;
import com.grego.tracker.entity.CheckPointResponse;
import com.grego.tracker.entity.LocationCheckPointDto;
import com.grego.tracker.repository.ICheckPointRepository;
import com.grego.tracker.repository.IGenericRepository;
import com.grego.tracker.repository.model.CheckPoint;
import com.grego.tracker.repository.model.CheckPointType;
import com.grego.tracker.repository.model.LocationCheckpoint;
import com.grego.tracker.service.ICheckpointService;
import com.grego.tracker.service.ICheckpointTypeService;
import com.grego.tracker.service.ILocationCheckpointService;

import reactor.core.publisher.Mono;

@Service
public class CheckpointServiceImpl extends CrudImpl<CheckPoint, Long> implements ICheckpointService {

	@Autowired
	private ICheckPointRepository repository;
	
	@Autowired
	private ILocationCheckpointService locationCheckpointService;
	
	@Autowired
	private ICheckpointTypeService checkpointTypeService;
	
	
	@Override
	protected IGenericRepository<CheckPoint, Long> getRepository() {
		// TODO Auto-generated method stub
		return repository;
	}

	@Override
	public Mono<CheckPointResponse> registrarCheckPoint(CheckPointRequest request) {
		
		return repository.save(toCheckPoint(request)).flatMap(checkpoint -> {

			return locationCheckpointService.registrar(toLocationCheckPoint(checkpoint, request)).flatMap(location -> {
				if (location != null) {
					return Mono.just(checkpoint);
				}
				return Mono.empty();
			});
		}).flatMap(checkpoint -> findById(checkpoint.getId()));		
		
	}

	private Mono<CheckPointResponse> findById(Long id) {
		
		return repository.findById(id)
				.map(this::toCheckPointResponse)
				.flatMap(checkpoint->
					Mono.just(checkpoint).zipWith(locationCheckpointService.listarPorIdCheckPoint(checkpoint.getCheckPointId()).defaultIfEmpty(LocationCheckpoint.builder().build()).map(this::toLocationCheckPointDto),(check,loc)->{					
						check.setCheckpointLocation(loc);
						return check;
					}).zipWith(checkpointTypeService.listarPorId(checkpoint.getCheckpointType().getId()).defaultIfEmpty(CheckPointType.builder().build()),(check,type)->{					
						check.setCheckpointType(type);
						return check;
					})	
				);
	}

	private CheckPointResponse toCheckPointResponse(CheckPoint checkpoint) {
		return CheckPointResponse
				.builder()
				.checkPointId(checkpoint.getId())
				.checkpointName(checkpoint.getCheckpointName())
				.checkpointComment(checkpoint.getCheckpointComment())
				.checkpointUserId(checkpoint.getIdCheckpointUserId())
				.checkpointType(CheckPointType.builder().id(checkpoint.getIdCheckpointType()).build())
				.checkpointTimestamp(checkpoint.getCheckpointTimestamp())
				.build();
	}
	private LocationCheckpoint toLocationCheckPoint(CheckPoint checkpoint, CheckPointRequest request) {
		return LocationCheckpoint
		.builder()
		.idCheckpoint(checkpoint.getId())
		.latitude(request.getCheckpointLocation().getLatitude())
		.longitude(request.getCheckpointLocation().getLongitude())
		.registryday(LocalDateTime.now())
		.build();
		
	}

	private CheckPoint toCheckPoint(CheckPointRequest request) {
		
		return CheckPoint
				.builder()
				.checkpointComment(request.getCheckpointComment())
				.checkpointName(request.getCheckpointName())
				.idCheckpointType(request.getCheckpointType().getId())
				.idCheckpointUserId(request.getCheckpointUserId())
				.idorder(request.getIdOrder())
				.checkpointTimestamp(LocalDateTime.now() )
				.build();		
	}

	private LocationCheckPointDto  toLocationCheckPointDto(LocationCheckpoint entity) {
		return LocationCheckPointDto.builder()
				.id(entity.getId())
				.idCheckPoint(entity.getIdCheckpoint())
				.latitude(entity.getLatitude())
				.longitude(entity.getLongitude())
				.registryday(entity.getRegistryday()).build();
	}

	@Override
	public Mono<CheckPointAllResponse> listarCheckPoints(Long idOrder) {
		return repository.findAllByIdOrder(idOrder)
		.map(this::toCheckPointResponse)
		.flatMap(checkpoint->
			Mono.just(checkpoint).zipWith(locationCheckpointService.listarPorIdCheckPoint(checkpoint.getCheckPointId()).defaultIfEmpty(LocationCheckpoint.builder().build()).map(this::toLocationCheckPointDto),(check,loc)->{					
				check.setCheckpointLocation(loc);
				return check;
			}).zipWith(checkpointTypeService.listarPorId(checkpoint.getCheckpointType().getId()).defaultIfEmpty(CheckPointType.builder().build()),(check,type)->{					
				check.setCheckpointType(type);
				return check;
			})	
		).collectList()
		.map(this::toCheckPointAllResponse);
	
	}
	
	private CheckPointAllResponse toCheckPointAllResponse(List<CheckPointResponse> listado) {
		
		return CheckPointAllResponse.builder().checkpoints(listado).build();
		
	}
//	private CheckPointType  toCheckPointTypeDto(CheckPointType entity) {
//		return LocationCheckPointDto.builder()
//				.id(entity.getId())
//				.idCheckPoint(entity.getIdCheckpoint())
//				.latitude(entity.getLatitude())
//				.longitude(entity.getLongitude())
//				.registryday(entity.getRegistryday()).build();
//	}
//	
	

}
