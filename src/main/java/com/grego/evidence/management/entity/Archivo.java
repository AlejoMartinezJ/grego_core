package com.grego.evidence.management.entity;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Archivo {

	private Integer idArchivo;
	private String filename;
	private String name;
	private String filetype;
	private byte[] value;
	@Override
	public String toString() {
		return "Archivo [idArchivo=" + idArchivo + ", filename=" + filename + ", name=" + name + ", filetype="
				+ filetype + ", value=" + Arrays.toString(value) + "]";
	}
	

}
