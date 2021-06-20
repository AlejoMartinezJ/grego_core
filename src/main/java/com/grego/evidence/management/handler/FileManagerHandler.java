package com.grego.evidence.management.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.grego.evidence.management.service.IFileInformationService;

import reactor.core.publisher.Mono;

@Component
public class FileManagerHandler {

	@Autowired
	private IFileInformationService fileService;
	
	@Value("${ruta.upload}")
	private String RUTA_UPLOAD;
	
	public Mono<ServerResponse> uploadFile(ServerRequest req){
		
		return req.body(BodyExtractors.toMultipartData())
		.flatMap(x->Mono.just( x.toSingleValueMap()))
		.flatMap(x->{
			return fileService.registrar(x);	
		})
		.flatMap(c -> ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(fromValue(c))
		);
	}
	
	public Mono<ServerResponse> updateFile(ServerRequest req){
		
		return req.body(BodyExtractors.toMultipartData())
		.flatMap(x->Mono.just( x.toSingleValueMap()))
		.flatMap(x->{
			return fileService.updateFile(x);	
		})
		.flatMap(c -> ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(fromValue(c))
		);
	}
	
	public Mono<ServerResponse> buscarporId(ServerRequest req){
		Long documentId = Long.parseLong(req.pathVariable("documentId"));
		return fileService.listarPorId(documentId)
		.flatMap(c -> ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(fromValue(c))
		);
	}
	
}
