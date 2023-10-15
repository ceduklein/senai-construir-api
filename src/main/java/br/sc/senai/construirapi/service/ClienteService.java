package br.sc.senai.construirapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.sc.senai.construirapi.exception.RulesException;
import br.sc.senai.construirapi.model.entity.Cliente;
import br.sc.senai.construirapi.model.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public Cliente save(Cliente cliente) throws RulesException {
		
		if (repository.existsByDoc(cliente.getDoc()))
			throw new RulesException("Documento já cadastrado.");
		
		if (repository.existsByEmail(cliente.getEmail()))
			throw new RulesException("Email já cadastrado.");
		
		Cliente savedCliente = new Cliente();
		savedCliente.setNome(cliente.getNome());
		savedCliente.setDoc(cliente.getDoc());
		savedCliente.setEmail(cliente.getEmail());
		savedCliente.setTelefone(cliente.getTelefone());
		
		return repository.save(savedCliente);
	}
	
	public List<Cliente> list() throws RulesException {
		return repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	public Cliente findById(Long id) throws RulesException {
		return validarCliente(id);
	}
	
	public Cliente update(Long id, Cliente cliente) throws RulesException {
		Cliente updatedCliente = validarCliente(id);
		
		if (repository.findByEmailWhere(id, cliente.getEmail()).isPresent())
			throw new RulesException("Email já cadastrado.");
		
		updatedCliente.setNome(cliente.getNome());
		updatedCliente.setEmail(cliente.getEmail());
		updatedCliente.setTelefone(cliente.getTelefone());
		
		return repository.save(updatedCliente);
	}
	
	public void delete(Long id) throws RulesException {
		Cliente cliente = validarCliente(id);
		repository.delete(cliente);
	}
	
	private Cliente validarCliente(Long id) throws RulesException {
		Optional<Cliente> cliente = repository.findById(id);
		if (!cliente.isPresent())
			throw new RulesException("Cliente não encontrado.");
		
		return cliente.get();
	}
}
