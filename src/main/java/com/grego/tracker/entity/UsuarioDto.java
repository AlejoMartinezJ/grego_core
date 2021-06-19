package com.grego.tracker.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {

	private Integer id;
	private String user;
	private String password;
	private String referencia;
	private String descripcion;

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", user=" + user + ", password=" + password + ", referencia=" + referencia
				+ ", descripcion=" + descripcion + "]";
	}

}
