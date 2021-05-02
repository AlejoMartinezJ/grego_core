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
public class LocationDto {

	private Long id;
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
