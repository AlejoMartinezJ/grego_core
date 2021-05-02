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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("tb_schedule")
public class Schedule {

	@Id
	private Long id;
	@Column("idOrder")
	private Long idOrder;
	private LocalDateTime deliverydate;
	private LocalDateTime registryday;

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", idOrder=" + idOrder + ", deliverydate=" + deliverydate + ", registryday="
				+ registryday + "]";
	}

}
