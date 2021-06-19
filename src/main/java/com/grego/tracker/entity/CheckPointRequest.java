package com.grego.tracker.entity;

import java.time.LocalDateTime;

import com.grego.tracker.repository.model.CheckPointType;
import com.grego.tracker.repository.model.LocationCheckpoint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckPointRequest {
	
	private Long idOrder;
	
	private CheckPointType checkpointType;
	
	private String checkpointName;
	
	private String checkpointComment;
	
	private LocationCheckpoint checkpointLocation;
	
	private Long checkpointEntityId; 
	
	private Long checkpointUserId;
	
	private LocalDateTime checkpointTimestamp;

}
