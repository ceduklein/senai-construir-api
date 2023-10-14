package br.sc.senai.construirapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.sc.senai.construirapi.model.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
