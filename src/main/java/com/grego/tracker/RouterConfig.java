package com.grego.tracker;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.grego.tracker.handler.CheckPointTypeHandler;
import com.grego.tracker.handler.LocationCheckPointHandler;
import com.grego.tracker.handler.TrackerHandler;

@Configuration
public class RouterConfig {

	
	
	@Bean
	public RouterFunction<ServerResponse> rutasTracker(TrackerHandler handler){
		return route(GET("/v1.0/tracker"), handler::listar)
				.andRoute(GET("/v1.0/tracker/filter"), handler::listarFilter)
				.andRoute(GET("/v1.0/tracker/detail/{orderId}"), handler::detail)
				.andRoute(POST("/v1.0/tracker/detail/{orderId}"), handler::registrar);
	}
	
//	@Bean
//	public RouterFunction<ServerResponse> rutasCheckPoint(CheckPointHandler handler){
//		return route(GET("/v1.0/order-detail"), handler::listar)
//				.andRoute(GET("/v1.0/order-detail/{id}"), handler::listarPorId)
//				.andRoute(POST("/v1.0/order-detail"), handler::registrar)
//				.andRoute(PUT("/v1.0/order-detail"), handler::modificar)
//				.andRoute(DELETE("/v1.0/order-detail/{id}"), handler::eliminar);
//	}
//		
	@Bean
	public RouterFunction<ServerResponse> rutasLocationCheckPoint(LocationCheckPointHandler handler){
		return route(GET("/v1.0/location-check-point"), handler::listar)
				.andRoute(GET("/v1.0/location-check-point/{id}"), handler::listarPorId)
				.andRoute(POST("/v1.0/location-check-point"), handler::registrar)
				.andRoute(PUT("/v1.0/location-check-point"), handler::modificar)
				.andRoute(DELETE("/v1.0/location-check-point/{id}"), handler::eliminar);
	}
	
	@Bean
	public RouterFunction<ServerResponse> rutasCheckPointType(CheckPointTypeHandler handler){
		return route(GET("/v1.0/check-point-type"), handler::listar)
				.andRoute(GET("/v1.0/check-point-type/{id}"), handler::listarPorId)
				.andRoute(POST("/v1.0/check-point-type"), handler::registrar)
				.andRoute(PUT("/v1.0/check-point-type"), handler::modificar)
				.andRoute(DELETE("/v1.0/check-point-type/{id}"), handler::eliminar);
	}
		
}
