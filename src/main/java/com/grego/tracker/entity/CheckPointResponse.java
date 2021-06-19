package com.grego.tracker.entity;

import java.time.LocalDateTime;

import com.grego.tracker.repository.model.CheckPointType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckPointResponse {
	
	private Long checkPointId;
	
	private CheckPointType checkpointType;
	
	private String checkpointName;
	
	private String checkpointComment;
	
	private LocationCheckPointDto checkpointLocation;
	
	private Long checkpointEntityId; 
	
	private Long checkpointUserId;
	
	private LocalDateTime checkpointTimestamp;


}
