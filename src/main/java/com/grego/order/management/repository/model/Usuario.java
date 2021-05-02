package com.grego.order.management.repository.model;

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
@Table("tb_usuario")
public class Usuario {

	@Id
	private Long id;
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
