package br.sc.senai.construirapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.sc.senai.construirapi.model.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
