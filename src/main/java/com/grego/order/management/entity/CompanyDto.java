package com.grego.order.management.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto {

	private Integer id;
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
