package com.grego.order.management.entity;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequest {

	private Long entityId;
	private String optionalAddress;
	private OrderTypeDto orderType;
	private Long orderId;
	private List<OrderDetailDto> orderDetail;
	private Long assignedEntityId;
	private Integer deliverysQuantity;
	private List<ScheduleDto> schedule;
	private Integer boxQuantity;
	private MeasureDto boxDetail;
	private LocationDto orderLocation;
	private Long orderUserId;
	private LocalDateTime orderTimestamp;

	@Override
	public String toString() {
		return "OrderRequest [entityId=" + entityId + ", optionalAddress=" + optionalAddress + ", orderType="
				+ orderType + ", orderId=" + orderId + ", orderDetail=" + orderDetail + ", assignedEntityId="
				+ assignedEntityId + ", deliverysQuantity=" + deliverysQuantity + ", schedule=" + schedule
				+ ", boxQuantity=" + boxQuantity + ", boxDetail=" + boxDetail + ", orderLocation=" + orderLocation
				+ ", orderUserId=" + orderUserId + ", orderTimestamp=" + orderTimestamp + "]";
	}

}
