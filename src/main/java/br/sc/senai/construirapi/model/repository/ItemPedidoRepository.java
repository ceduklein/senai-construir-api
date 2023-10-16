package br.sc.senai.construirapi.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.sc.senai.construirapi.model.entity.ItemPedido;
import br.sc.senai.construirapi.model.entity.Pedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {
	
	List<ItemPedido> findByPedido(Pedido pedido);

}
