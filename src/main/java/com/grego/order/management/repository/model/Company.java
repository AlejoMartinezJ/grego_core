package com.grego.order.management.repository.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_company")
public class Company {

	@Id
	private Long id;
	private String name;
	private Integer ruc;
	private String location;
	private String description;
	private LocalDateTime registryday;
	
	

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", ruc=" + ruc + ", location=" + location + ", description="
				+ description + ", registryday=" + registryday + "]";
	}

}
