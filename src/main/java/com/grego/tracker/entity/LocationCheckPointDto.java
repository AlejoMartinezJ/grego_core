package com.grego.tracker.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationCheckPointDto {

	private Long id;
	private Long idCheckPoint;
	private String longitude;
	private String latitude;
	private LocalDateTime registryday;
}
