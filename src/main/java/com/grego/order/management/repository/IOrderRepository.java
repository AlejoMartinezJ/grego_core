package com.grego.order.management.repository;

import org.springframework.data.r2dbc.repository.Query;

import com.grego.order.management.repository.model.Order;

import reactor.core.publisher.Flux;

public interface IOrderRepository extends IGenericRepository<Order, Long>{
	//	@Query("select O.id, O.idEntity, O.optionalAddress, O.idOrderType  from order O where name = $1")

	
	@Query("SELECT tor.id ,\r\n"
			+ "       tor.idEntity as idEntity,\r\n"
			+ "       tor.optionalAddress as optionalAddress,\r\n"
			+ "       tor.idOrderType as idOrderType,\r\n"
			+ "       tor.idOrderDetail as idOrderDetail,\r\n"
			+ "       tor.idAssignedEntity as idAssignedEntity,\r\n"
			+ "       tor.deliverysQuantity as deliverysQuantity ,\r\n"
			+ "       tor.idSchedule as idSchedule,\r\n"
			+ "       tor.boxQuantity as boxQuantity,\r\n"
			+ "       tor.idBoxDetail as idMeasure,\r\n"
			+ "       tor.idOrderLocation as idLocation,\r\n"
			+ "       tor.idOrderUser as idOrderUser,\r\n"
			+ "       tor.orderTimestamp as orderTimestamp,\r\n"
			+ "   	  ort.name as nameOrderType,\r\n"
			+ "	   	  ort.description as descrptionOrderType,\r\n"
			+ "	      loc.longitude as longitude,\r\n"
			+ "	      loc.latitude as latitude,\r\n"
			+ "	      mea.length as length,\r\n"
			+ "	      mea.width as width,\r\n"
			+ "	      mea.depth as depth\r\n"
			+ "FROM tb_order tor\r\n"
			+ "INNER JOIN tb_ordertype ort ON tor.idOrderType = ort.id\r\n"
			+ "INNER JOIN tb_location loc on tor.idOrderLocation = loc.id\r\n"
			+ "INNER JOIN tb_measure mea on tor.idBoxDetail = mea.id;\r\n"
			+ "\r\n"
			+ "")
//	@Query("SELECT id, \"idEntity\", \"optionalAddress\", \"idOrderType\", \"idOrderDetail\", \"idAssignedEntity\", \"deliverysQuantity\", \"idSchedule\", \"boxQuantity\", \"idBoxDetail\", \"idOrderLocation\", \"idOrderUser\", \"orderTimestamp\" FROM tb_order")
	Flux<Order> findOrderRelationsAll();
}
