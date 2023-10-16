package br.sc.senai.construirapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {

	private Long id;
	private Long id_pedido;
	private Long id_produto;
	private Integer qtde;
	private Double totalItem;
}
