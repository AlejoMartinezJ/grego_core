package com.grego.order.management.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grego.order.management.entity.LocationDto;
import com.grego.order.management.entity.MeasureDto;
import com.grego.order.management.entity.OrderDetailDto;
import com.grego.order.management.entity.OrderRequest;
import com.grego.order.management.entity.OrderResponse;
import com.grego.order.management.entity.OrderTypeDto;
import com.grego.order.management.entity.ScheduleDto;
import com.grego.order.management.repository.IGenericRepository;
import com.grego.order.management.repository.IOrderRepository;
import com.grego.order.management.repository.model.Location;
import com.grego.order.management.repository.model.Measure;
import com.grego.order.management.repository.model.Order;
import com.grego.order.management.repository.model.OrderDetail;
import com.grego.order.management.repository.model.OrderType;
import com.grego.order.management.repository.model.Schedule;
import com.grego.order.management.service.ILocationService;
import com.grego.order.management.service.IMeasureService;
import com.grego.order.management.service.IOrderService;
import com.grego.order.management.service.IOrderdetailService;
import com.grego.order.management.service.IOrdertypeService;
import com.grego.order.management.service.ISchedulerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OrderServiceImpl extends CrudImpl<Order, Long> implements IOrderService{

	@Autowired
	private IOrderRepository repository;
	
	@Autowired
	private IOrderdetailService orderdetailService;
	
	@Autowired
	private ISchedulerService schedulerService;

	@Autowired
	private IOrdertypeService ordertypeService;
	
	@Autowired
	private IMeasureService measureService;
	
	@Autowired
	private ILocationService locationService;
	
	@Override
	protected IGenericRepository<Order, Long> getRepository() {
		// TODO Auto-generated method stub
		return repository;
	}
	
	@Override
	public Flux<Order> findOrderRelationsAll() {
		// TODO Auto-generated method stub
		
		return repository.findOrderRelationsAll();
	}

	@Override
	public Flux<OrderResponse> findAllOrder() {
		System.err.println("entroo findallorder");

		return repository.findAll()
		.map(this::toOrderResponse)
		.flatMap(order ->{		
			System.err.println(order.toString());

			return Mono.just(order).zipWith(ordertypeService.listarPorId(order.getIdOrderType()).map(this::toOrderTypeDto),(ord,orTy)->{					
				ord.setOrderType(orTy);
				return ord;
			}).zipWith(orderdetailService.listarPorIdOrder(order.getOrderId()).defaultIfEmpty(OrderDetail.builder().build()).map(this::toOrderDetailDto).collectList().defaultIfEmpty(new ArrayList<>()),(ord,det)->{									
				ord.setOrderDetail(det);
				return ord;
			}).zipWith(schedulerService.listarPorIdOrder(order.getOrderId()).defaultIfEmpty(Schedule.builder().build()).map(this::toScheduleDto).collectList(),(ord,sched)->{					
				ord.setSchedule(sched);
				return ord;
			}).zipWith(measureService.listarPorIdOrder(order.getOrderId()).defaultIfEmpty(Measure.builder().build()).map(this::toMeasureDto),(ord,measu)->{	
				ord.setBoxDetail(measu);
				return ord;
			}).zipWith(locationService.listarPorIdOrder(order.getOrderId()).defaultIfEmpty(Location.builder().build()).map(this::toLocationDto),(ord,loc)->{					
				ord.setOrderLocation(loc);
				return ord;
			});
			
		});
		
	}
	
	@Override
	public Mono<OrderResponse> findById(Long id) {
		// TODO Auto-generated method stub
		System.err.println("entro a find by id ");
		return repository.findById(id)								
				.map(this::toOrderResponse)
				.flatMap(order ->{	
					System.err.println(order.toString());
					return Mono.just(order).zipWith(ordertypeService.listarPorId(order.getIdOrderType()).map(this::toOrderTypeDto),(ord,orTy)->{					
						ord.setOrderType(orTy);
						return ord;
					}).zipWith(orderdetailService.listarPorIdOrder(order.getOrderId()).defaultIfEmpty(OrderDetail.builder().build()).map(this::toOrderDetailDto).collectList(),(ord,det)->{					
						ord.setOrderDetail(det);
						return ord;
					}).zipWith(schedulerService.listarPorIdOrder(order.getOrderId()).defaultIfEmpty(Schedule.builder().build()).map(this::toScheduleDto).collectList(),(ord,sched)->{					
						ord.setSchedule(sched);
						return ord;
					}).zipWith(measureService.listarPorIdOrder(order.getOrderId()).defaultIfEmpty(Measure.builder().build()).map(this::toMeasureDto),(ord,measu)->{	
						ord.setBoxDetail(measu);
						return ord;
					}).zipWith(locationService.listarPorIdOrder(order.getOrderId()).defaultIfEmpty(Location.builder().build()).map(this::toLocationDto),(ord,loc)->{					
						ord.setOrderLocation(loc);
						return ord;
					});
														
			});
	}
	


	@Override
	public Mono<OrderResponse> registrarOrder(OrderRequest request) {
		return repository.save(toOrder(request))
		.flatMap(order ->{
			return orderdetailService.registrarAll(toOrderDetailList(order,request))
		 	.flatMap(orddet ->{
		 		return Mono.just(orddet);
		 	}).collectList()
		 	.flatMap(list ->{
		 		if (list.size()>0) {
			 	 	return Mono.just(order); 					
				}
		 		return Mono.empty();			 	
		 	});		 	
		})
		.flatMap(order->{
			return schedulerService.registrarAll(toScheduleList(order,request))
			.flatMap(sche ->{
		 		System.err.println(sche.toString());
		 		return Mono.just(sche);
		 	}).collectList()
		 	.flatMap(list ->{
		 		if (list.size()>0) {
			 	 	return Mono.just(order); 					
				}
		 		return Mono.empty();			 	
		 	});
		})
		.flatMap(order->{
		 	return measureService.registrar(toMeasure(order,request))
		 	.flatMap(me ->{
		 		if (me != null ) {
			 		System.err.println(me.toString());
				 	return Mono.just(order);
				}
		 		return Mono.empty();
		 	});
		})
		.flatMap(order->{
			return locationService.registrar(toLocation(order,request))
		 	.flatMap(loc ->{
		 		if (loc != null ) {
			 		System.err.println(loc.toString());
				 	return Mono.just(order);
				}
		 		return Mono.empty();
		 	});
		})
		.flatMap(ord-> findById(ord.getId()));
		
	}
	
	@Override
	public Mono<OrderResponse> modificarOrder(OrderRequest request) {
		// TODO Auto-generated method stub
		return repository.save(toOrderUpdate(request))
				.flatMap(order ->{
					return orderdetailService
							.listarPorIdOrder(order.getId())
							.collectList()
							.flatMap( list->{
								orderdetailService.eliminarAll(list).subscribe();
								orderdetailService.registrarAll(toOrderDetailList(order,request)).subscribe();
								return Mono.just(order);
							});
				})
				.flatMap(order ->{
					return schedulerService
							.listarPorIdOrder(order.getId())
							.collectList()
							.flatMap(list->{
								schedulerService.eliminarAll(list).subscribe();
								schedulerService.registrarAll(toScheduleList(order,request)).subscribe();
								return Mono.just(order);
							});
				})
				.flatMap(order ->{
					return measureService
							.listarPorIdOrder(order.getId())
							.flatMap( item-> {							
								measureService.eliminar(item.getId()).subscribe();
								measureService.registrar(toMeasure(order,request)).subscribe();
								return Mono.just(order);
							});
				})
				.flatMap(order ->{
					return locationService
							.listarPorIdOrder(order.getId())
							.flatMap(item->{
								System.err.println(item.toString());
	  						    locationService.eliminar(item.getId()).subscribe();
								return locationService.registrar(toLocation(order,request)).flatMap(loc->{
									return Mono.just(order);	
								});
							});	
				})
				.flatMap(ord-> findById(ord.getId()));
				
	}
	
	private Location toLocation(Order order, OrderRequest request) {
		// TODO Auto-generated method stub
		return Location
				.builder()
				.idOrder(order.getId())				
				.latitude(request.getOrderLocation().getLatitude())
				.longitude(request.getOrderLocation().getLongitude())
				.build();
	}

	private Measure toMeasure(Order order, OrderRequest request) {
		// TODO Auto-generated method stub
		return Measure
				.builder()
				.idOrder(order.getId())
				.depth(request.getBoxDetail().getDepth())
				.length(request.getBoxDetail().getLength())
				.width(request.getBoxDetail().getWidth())
				.build();
	}

	private Flux<Schedule> toScheduleList(Order order, OrderRequest request) {
	 return Flux.fromIterable(request.getSchedule()).flatMap(schedule->{
			 
			 return Mono.just( Schedule
			 .builder()
			 .idOrder(order.getId())
			 .deliverydate(schedule.getDeliverydate())
			 .build());
			 			 			
		});
	}

	private Flux<OrderDetail> toOrderDetailList(Order order, OrderRequest request) {
		// TODO Auto-generated method stub
		
		 return Flux.fromIterable(request.getOrderDetail()).flatMap(detail->{
			 
			 return Mono.just( OrderDetail
			 .builder()
			 .idOrder(order.getId())
			 .idProducto(detail.getIdProducto())
			 .presentation(detail.getPresentation())
			 .product(detail.getProduct())
			 .quantity(detail.getQuantity())
			 .build());
			 			 			
		});
	}
	

	private Order toOrder(OrderRequest request) {
		
		return Order
				.builder()
				.idEntity(request.getEntityId())
				.optionalAddress(request.getOptionalAddress())
				.idOrderType(request.getOrderType().getId())
				.idAssignedEntity(request.getAssignedEntityId())
				.deliverysQuantity(request.getDeliverysQuantity())
				.boxQuantity(request.getBoxQuantity())
				.idOrderUser(request.getOrderUserId())
				.orderTimestamp(LocalDateTime.now())				
				.build();
				
		
		
	}
	
	private Order toOrderUpdate(OrderRequest request) {
		
		return Order
				.builder()
				.id(request.getOrderId())
				.idEntity(request.getEntityId())
				.optionalAddress(request.getOptionalAddress())
				.idOrderType(request.getOrderType().getId())
				.idAssignedEntity(request.getAssignedEntityId())
				.deliverysQuantity(request.getDeliverysQuantity())
				.boxQuantity(request.getBoxQuantity())
				.idOrderUser(request.getOrderUserId())
				.orderTimestamp(LocalDateTime.now())				
				.build();
				
		
		
	}
	
	
	private LocationDto toLocationDto(Location entity ){
		return LocationDto.builder()
				.id(entity.getId())
				.idOrder(entity.getIdOrder())
				.latitude(entity.getLatitude())
				.longitude(entity.getLongitude())
				.registryday(entity.getRegistryday()).build();
	}

	
	private MeasureDto toMeasureDto(Measure entity ){
		
		return MeasureDto.builder()
				.id(entity.getId())
				.idOrder(entity.getIdOrder())
				.depth(entity.getDepth())
				.length(entity.getLength())
				.width(entity.getWidth())
				.registryday(entity.getRegistryday()).build();
	}
	
	private OrderTypeDto toOrderTypeDto(OrderType entity ){
		return OrderTypeDto.builder()
				.description(entity.getDescription())
				.id(entity.getId())
				.name(entity.getName())
				.registryday(entity.getRegistryday()).build();
	}

	private OrderDetailDto toOrderDetailDto(OrderDetail entity) {
		System.err.println("ENTRO toOrderDetailDto ");
		return OrderDetailDto
		.builder()
		.id(entity.getId())
		.idProducto(entity.getIdProducto())
		.idOrder(entity.getIdOrder())
		.product(entity.getProduct())
		.presentation(entity.getPresentation())
		.quantity(entity.getQuantity())
		.registryday(entity.getRegistryday()).build();
	}
		
	private ScheduleDto toScheduleDto(Schedule entity) {
		return ScheduleDto
		.builder()
		.id(entity.getId())
		.idOrder(entity.getIdOrder())
		.deliverydate(entity.getDeliverydate())
		.registryday(entity.getRegistryday()).build();
	}

	private OrderResponse  toOrderResponse(Order data) {
		
		return OrderResponse
		.builder()
		.entityId(data.getIdEntity())
		.optionalAddress(data.getOptionalAddress())
		.orderId(data.getId())
		.assignedEntityId(data.getIdAssignedEntity())
		.deliverysQuantity(data.getDeliverysQuantity())
		.boxQuantity(data.getBoxQuantity())
		.orderUserId(data.getIdOrderUser())
		.orderTimestamp(data.getOrderTimestamp())
		.idOrderType(data.getIdOrderType())
		.idBoxDetail(data.getIdBoxDetail())
		.idLocation(data.getIdOrderLocation())
		.build();
		
	
	}

}
