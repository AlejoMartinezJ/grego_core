package com.grego.evidence.management.repository.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("tb_file")
public class FileInformation {

	@Id
	private Long id;
	private Long idorder;
	private String signature;
	private String format;
	private String resourcetype;
	private String secureurl;
	private String type;
	private String publicid;
	private String originalfilename;
	private LocalDateTime registryday;

	@Override
	public String toString() {
		return "FileInformation [id=" + id + ", idorder=" + idorder + ", signature=" + signature + ", format=" + format
				+ ", resourcetype=" + resourcetype + ", secureurl=" + secureurl + ", type=" + type + ", publicid="
				+ publicid + ", originalfilename=" + originalfilename + ", registryday=" + registryday + "]";
	}

}
