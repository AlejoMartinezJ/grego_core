package com.grego.order.management.repository.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("tb_ordertype")
public class OrderType {

	@Id
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
