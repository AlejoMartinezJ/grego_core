package com.grego.order.management.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderTypeDto {

	private Long id;
	private String name;
	private String description;
	private LocalDateTime registryday;

	@Override
	public String toString() {
		return "Ordertype [id=" + id + ", name=" + name + ", description=" + description + ", registryday="
				+ registryday + "]";
	}

}
