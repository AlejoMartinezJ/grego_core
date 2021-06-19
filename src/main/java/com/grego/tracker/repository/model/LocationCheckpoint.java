package com.grego.tracker.repository.model;

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
@Table("tb_locationcheckpoint")
public class LocationCheckpoint {

	@Id
	private Long id;
	
	@Column("idCheckpoint")	
	private Long idCheckpoint;
	private String longitude;
	private String latitude;
	private LocalDateTime registryday;

}
