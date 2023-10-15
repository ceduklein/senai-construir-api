package br.sc.senai.construirapi.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.sc.senai.construirapi.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	boolean existsByDoc(String doc);
	
	boolean existsByEmail(String email);
	
	@Query("from Cliente c where c.id != :id and c.email = :email")
	Optional<Cliente> findByEmailWhere(@Param("id") Long id, @Param("email") String email);

}
