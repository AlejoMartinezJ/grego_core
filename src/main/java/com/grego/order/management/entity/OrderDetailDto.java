package com.grego.order.management.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailDto {

	private Long id;
	private Long idOrder;
	private Integer idProducto;
	private String product;
	private String presentation;
	private Integer quantity;
	private LocalDateTime registryday;

	@Override
	public String toString() {
		return "Orderdetail [id=" + id + ", idOrder=" + idOrder + ", idProducto=" + idProducto + ", product=" + product
				+ ", presentation=" + presentation + ", quantity=" + quantity + ", registryday=" + registryday + "]";
	}

}
