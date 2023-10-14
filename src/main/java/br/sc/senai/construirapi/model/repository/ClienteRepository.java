package br.sc.senai.construirapi.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.sc.senai.construirapi.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	boolean existsByDoc(String doc);
	
	boolean existsByEmail(String email);

}
