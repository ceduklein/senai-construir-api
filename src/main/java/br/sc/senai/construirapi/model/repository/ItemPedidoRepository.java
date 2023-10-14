package br.sc.senai.construirapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.sc.senai.construirapi.model.entity.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

}
