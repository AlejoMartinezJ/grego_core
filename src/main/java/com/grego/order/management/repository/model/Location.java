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
@Table("tb_location")
public class Location {

	@Id
	private Long id;
	
	@Column("idOrder")	
	private Long idOrder;
	private String longitude;
	private String latitude;
	private LocalDateTime registryday;

	@Override
	public String toString() {
		return "Location [id=" + id + ", idOrder=" + idOrder + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", registryday=" + registryday + "]";
	}

}
