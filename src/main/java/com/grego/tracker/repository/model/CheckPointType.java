package com.grego.tracker.repository.model;

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
@Table("tb_checkpointtype")
public class CheckPointType {

	@Id
	private Long id;
	private String descripcion;
	
}
