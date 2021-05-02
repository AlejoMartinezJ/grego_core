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
@Table("tb_measure")
public class Measure {

	@Id
	private Long id;
	@Column("idOrder")
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
