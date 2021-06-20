package com.grego.evidence.management;


import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.grego.evidence.management.handler.FileManagerHandler;


@Configuration
public class RouterConfig {

	@Bean
	public RouterFunction<ServerResponse> rutasOrder(FileManagerHandler handler) {
		return route(POST("/v1.0/evidence-management"), handler::uploadFile)
				.andRoute(PUT("/v1.0/evidence-management"), handler::updateFile)
				.andRoute(GET("/v1.0/evidence-management/{documentId}"), handler::buscarporId);
	}
}
