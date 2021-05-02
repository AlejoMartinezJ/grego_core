package com.grego.order.management.repository.model;

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
@Table("tb_orderdetail")
public class OrderDetail {

	@Id
	private Long id;
	@Column("idOrder")
	private Long idOrder;
	@Column("idProducto")
	private Integer idProducto;
	@Column("product")
	private String product;
	@Column("presentation")
	private String presentation;
	@Column("quantity")
	private Integer quantity;
	@Column("registryday")
	private LocalDateTime registryday;

	@Override
	public String toString() {
		return "Orderdetail [id=" + id + ", idOrder=" + idOrder + ", idProducto=" + idProducto + ", product=" + product
				+ ", presentation=" + presentation + ", quantity=" + quantity + ", registryday=" + registryday + "]";
	}

}
