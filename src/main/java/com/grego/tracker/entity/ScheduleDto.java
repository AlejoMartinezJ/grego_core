package com.grego.tracker.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScheduleDto {

	private Long id;
	private Long idOrder;
	private LocalDateTime deliverydate;
	private LocalDateTime registryday;

	@Override
	public String toString() {
		return "Schedule [id=" + id + ", idOrder=" + idOrder + ", deliverydate=" + deliverydate + ", registryday="
				+ registryday + "]";
	}

}
