package br.sc.senai.construirapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

	private Long id;
	private Long id_cliente;
	private Double total;
}
