package com.grego.order.management.repository.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Table("tb_order")
public class Order {

	@Id
	private Long id;
	@Column("idEntity")	
	private Long idEntity;
	@Column("optionalAddress")	
	private String optionalAddress;
	@Column("idOrderType")
	private Long idOrderType;
	@Column("idOrderDetail")	
	private Long idOrderDetail;
	@Column("idAssignedEntity")	
	private Long idAssignedEntity;
	@Column("deliverysQuantity")	
	private Integer deliverysQuantity;
	@Column("idSchedule")	
	private Long idSchedule;
	@Column("boxQuantity")	
	private Integer boxQuantity;
	@Column("idBoxDetail")	
	private Long idBoxDetail;
	@Column("idOrderLocation")	
	private Long idOrderLocation;
	@Column("idOrderUser")	
	private Long idOrderUser;
	@Column("orderTimestamp")	
	private LocalDateTime orderTimestamp;
	
//	@Column("nameOrderType")	
//	private String nameOrderType;
//	@Column("descrptionOrderType")	
//	private String descrptionOrderType;
//	@Column("longitude")	
//	private String longitude;
//	@Column("latitude")	
//	private String latitude;
//	@Column("length")	
//	private Double length;
//	@Column("width")	
//	private Double width;
//	@Column("depth")	
//	private Double depth;
	
	
	
}
