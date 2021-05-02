package com.grego.order.management;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.grego.order.management.handler.CompanyHandler;
import com.grego.order.management.handler.LocationHandler;
import com.grego.order.management.handler.MeasureHandler;
import com.grego.order.management.handler.OrderDetailHandler;
import com.grego.order.management.handler.OrderHandler;
import com.grego.order.management.handler.OrderTypeHandler;
import com.grego.order.management.handler.ScheduleHandler;

@Configuration
public class RouterConfig {

	@Bean
	public RouterFunction<ServerResponse> rutasOrder(OrderHandler handler) {
		return route(GET("/v1.0/order-management"), handler::listar)
				.andRoute(GET("/v1.0/order-management/{id}"), handler::listarPorId)
				.andRoute(POST("/v1.0/order-management"), handler::registrar)
				.andRoute(PUT("/v1.0/order-management"), handler::modificar)
				.andRoute(DELETE("/v1.0/order-management/{id}"), handler::eliminar);
	}
		
	@Bean
	public RouterFunction<ServerResponse> rutasCompany(CompanyHandler handler){
		return route(GET("/v1.0/company"), handler::listar)
				.andRoute(GET("/v1.0/company/{id}"), handler::listarPorId)
				.andRoute(POST("/v1.0/company"), handler::registrar)
				.andRoute(PUT("/v1.0/company"), handler::modificar)
				.andRoute(DELETE("/v1.0/company/{id}"), handler::eliminar);
	}

	
	@Bean
	public RouterFunction<ServerResponse> rutasLocation(LocationHandler handler){
		return route(GET("/v1.0/location"), handler::listar)
				.andRoute(GET("/v1.0/location/{id}"), handler::listarPorId)
				.andRoute(POST("/v1.0/location"), handler::registrar)
				.andRoute(PUT("/v1.0/location"), handler::modificar)
				.andRoute(DELETE("/v1.0/location/{id}"), handler::eliminar);
	}
	
	@Bean
	public RouterFunction<ServerResponse> rutasMeasure(MeasureHandler handler){
		return route(GET("/v1.0/measure"), handler::listar)
				.andRoute(GET("/v1.0/measure/{id}"), handler::listarPorId)
				.andRoute(POST("/v1.0/measure"), handler::registrar)
				.andRoute(PUT("/v1.0/measure"), handler::modificar)
				.andRoute(DELETE("/v1.0/measure/{id}"), handler::eliminar);
	}
	
	@Bean
	public RouterFunction<ServerResponse> rutasOrderDetail(OrderDetailHandler handler){
		return route(GET("/v1.0/order-detail"), handler::listar)
				.andRoute(GET("/v1.0/order-detail/{id}"), handler::listarPorId)
				.andRoute(POST("/v1.0/order-detail"), handler::registrar)
				.andRoute(PUT("/v1.0/order-detail"), handler::modificar)
				.andRoute(DELETE("/v1.0/order-detail/{id}"), handler::eliminar);
	}
	
	@Bean
	public RouterFunction<ServerResponse> rutasOrderType(OrderTypeHandler handler){
		return route(GET("/v1.0/order-type"), handler::listar)
				.andRoute(GET("/v1.0/order-type/{id}"), handler::listarPorId)
				.andRoute(POST("/v1.0/order-type"), handler::registrar)
				.andRoute(PUT("/v1.0/order-type"), handler::modificar)
				.andRoute(DELETE("/v1.0/order-type/{id}"), handler::eliminar);
	}
	
	@Bean
	public RouterFunction<ServerResponse> rutasSchedule(ScheduleHandler handler){
		return route(GET("/v1.0/schedule"), handler::listar)
				.andRoute(GET("/v1.0/schedule/{id}"), handler::listarPorId)
				.andRoute(POST("/v1.0/schedule"), handler::registrar)
				.andRoute(PUT("/v1.0/schedule"), handler::modificar)
				.andRoute(DELETE("/v1.0/schedule/{id}"), handler::eliminar);
	}

	
	
}
