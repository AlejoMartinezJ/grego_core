package com.grego.tracker.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.grego.tracker.entity.CheckPointRequest;
import com.grego.tracker.repository.model.CheckPoint;
import com.grego.tracker.service.ICheckpointService;
import com.grego.tracker.validators.RequestValidator;

import reactor.core.publisher.Mono;

@Component
public class TrackerHandler {

	@Autowired
	private ICheckpointService service;
	
	@Autowired
	private RequestValidator validadorGeneral;
	
	public Mono<ServerResponse> listar(ServerRequest req){
		Long entityId = Long.parseLong(req.queryParam("entityId").get());
		Long entityTimeLapse = Long.parseLong(req.queryParam("entityTimeLapse").get());
		System.err.println(entityId);
		System.err.println(entityTimeLapse);
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.listar(), CheckPoint.class);
	}
		
	public Mono<ServerResponse> listarFilter(ServerRequest req){
		Long id = Long.parseLong(req.pathVariable("id"));
		return service.listarPorId(id)
				.flatMap(c -> 
						ServerResponse
							.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(fromValue(c))						
				).switchIfEmpty(ServerResponse
						.notFound()
						.build()
				);				
	}
	
	public Mono<ServerResponse> detail(ServerRequest req){
		Long id = Long.parseLong(req.pathVariable("orderId"));
		return service.listarCheckPoints(id)
				.flatMap(c -> 
						ServerResponse
							.ok()
							.contentType(MediaType.APPLICATION_JSON)
							.body(fromValue(c))						
				).switchIfEmpty(ServerResponse
						.notFound()
						.build()
				);				
	}
	
	
	public Mono<ServerResponse> registrar(ServerRequest req){
		
		Mono<CheckPointRequest> request = req.bodyToMono(CheckPointRequest.class).flatMap(requesta->{
			Long idOrder = Long.parseLong(req.pathVariable("orderId"));
			requesta.setIdOrder(idOrder);
			return Mono.just( requesta);
		});
		
		return request
				//.flatMap(validadorGeneral::validate)
				.flatMap(service::registrarCheckPoint)
				.flatMap(p -> ServerResponse
						.created(URI.create(req.uri().toString().concat("/").concat(p.getCheckPointId().toString())))
						.contentType(MediaType.APPLICATION_JSON)
						.body(fromValue(p))
				);
	}
	
	public Mono<ServerResponse> modificar(ServerRequest req){
		
		Mono<CheckPoint> request = req.bodyToMono(CheckPoint.class);
		
		return request
				.flatMap(validadorGeneral::validate)
				.flatMap(service::modificar)
				.flatMap(c -> ServerResponse
						.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(fromValue(c))
				);
	}
	
	public Mono<ServerResponse> eliminar(ServerRequest req){
		Long id = Long.parseLong(req.pathVariable("id"));
		return service.listarPorId(id)	
				.flatMap(c-> service.eliminar(c.getId())				
						.then(ServerResponse
								.noContent()
								.build()
								)
				//CORREGIDO (Ver en el video que el switchIfEmpty estaba dentro del bloque flatMap, debió estar fuera)					
				).switchIfEmpty(ServerResponse
						.notFound()
						.build()
				);	
	}
}
