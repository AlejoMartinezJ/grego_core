package com.grego.tracker.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.grego.tracker.repository.model.Usuario;
import com.grego.tracker.service.IUsuarioService;

import reactor.core.publisher.Mono;

@Component
public class UsuarioHandler {

	@Autowired
	private IUsuarioService service;

	public Mono<ServerResponse> listar(ServerRequest request) {

		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.listar(), Usuario.class);
	}
	
	public Mono<ServerResponse> listarPorId(ServerRequest req){
		Long id = Long.getLong(req.pathVariable("id"));
		return service.listarPorId(id)
				.flatMap(p -> ServerResponse
						.ok()
						.contentType(MediaType.APPLICATION_JSON)
						.body(fromValue(p))
				)
				.switchIfEmpty(ServerResponse
						.notFound()
						.build()
				);				
	}
}
