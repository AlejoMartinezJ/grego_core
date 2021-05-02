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
public class MeasureDto {

	private Long id;
	private Long idOrder;
	private Double length;
	private Double width;
	private Double depth;
	private LocalDateTime registryday;

	@Override
	public String toString() {
		return "Measure [id=" + id + ", idOrder=" + idOrder + ", length=" + length + ", width=" + width + ", depth="
				+ depth + ", registryday=" + registryday + "]";
	}

}
