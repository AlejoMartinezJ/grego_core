package com.grego.tracker.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICrud<T, ID> {

	Mono<T> registrar(T obj);

	Mono<T> modificar(T obj);

	Flux<T> listar();

	Mono<T> listarPorId(ID obj);

	Mono<Void> eliminar(ID obj);
}
