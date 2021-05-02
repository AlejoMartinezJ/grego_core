package com.grego.order.management.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.grego.order.management.repository.model.Company;
import com.grego.order.management.service.ICompanyService;
import com.grego.order.management.validators.RequestValidator;

import reactor.core.publisher.Mono;

@Component
public class CompanyHandler {

	@Autowired
	private ICompanyService service;
	
	@Autowired
	private RequestValidator validadorGeneral;
	
	public Mono<ServerResponse> listar(ServerRequest req){
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.listar(), Company.class);
	}
	
	public Mono<ServerResponse> listarPorId(ServerRequest req){
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
	
	public Mono<ServerResponse> registrar(ServerRequest req){
		
		Mono<Company> company = req.bodyToMono(Company.class);
		return company
				//.flatMap(validadorGeneral::validate)
				.flatMap(service::registrar)
				.flatMap(p -> ServerResponse
						.created(URI.create(req.uri().toString().concat("/").concat(p.getId().toString())))
						.contentType(MediaType.APPLICATION_JSON)
						.body(fromValue(p))
				);
	}
	
	public Mono<ServerResponse> modificar(ServerRequest req){
		
		Mono<Company> platoMono = req.bodyToMono(Company.class);
		
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
