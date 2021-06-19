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
@Table("tb_checkpoint")
public class CheckPoint {
	
	@Id
	private Long id;
	
	@Column("idcheckpointtype")	
	private Long idCheckpointType;
	
	@Column("checkpointname")	
	private String checkpointName;
	
	@Column("checkpointcomment")	
	private String checkpointComment;
	
	private Long idorder;
	//private Long idCheckpointLocation; 
	
	@Column("idcheckpointuserid")	
	private Long idCheckpointUserId;
	
	@Column("checkpointtimestamp")
	private LocalDateTime checkpointTimestamp;
	
}
