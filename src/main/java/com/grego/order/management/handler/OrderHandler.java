package com.grego.order.management.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.grego.order.management.entity.OrderRequest;
import com.grego.order.management.repository.model.Order;
import com.grego.order.management.service.IOrderService;
import com.grego.order.management.validators.RequestValidator;

import reactor.core.publisher.Mono;

@Component
public class OrderHandler {

	@Autowired
	private IOrderService service;
	
	@Autowired
	private RequestValidator validadorGeneral;
	
//	public Mono<ServerResponse> listar(ServerRequest req){
//		return ServerResponse
//				.ok()
//				.contentType(MediaType.APPLICATION_JSON)
//				.body(service.findOrderRelationsAll(), Order.class);
//	}
	
	
	public Mono<ServerResponse> listar(ServerRequest req){
		System.err.println("entro aqui 1");
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.findAllOrder(), OrderRequest.class);
	}
	
	public Mono<ServerResponse> listarPorId(ServerRequest req){
		System.err.println("entro aqui 2");

		Long id = Long.parseLong(req.pathVariable("id"));
		return service.findById(id)
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
		
		Mono<OrderRequest> request = req.bodyToMono(OrderRequest.class);
		
		return request
				//.flatMap(validadorGeneral::validate)
				.flatMap(service::registrarOrder)
				.flatMap(p -> ServerResponse
						.created(URI.create(req.uri().toString().concat("/").concat(p.getOrderId().toString())))
						.contentType(MediaType.APPLICATION_JSON)
						.body(fromValue(p))
				);
	}
	
	public Mono<ServerResponse> modificar(ServerRequest req){
		
		Mono<Order> platoMono = req.bodyToMono(Order.class);
		
		return platoMono
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
