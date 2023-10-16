package br.sc.senai.construirapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.sc.senai.construirapi.dto.PedidoDTO;
import br.sc.senai.construirapi.exception.RulesException;
import br.sc.senai.construirapi.model.entity.Cliente;
import br.sc.senai.construirapi.model.entity.Pedido;
import br.sc.senai.construirapi.model.repository.ItemPedidoRepository;
import br.sc.senai.construirapi.model.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	@Autowired 
	ItemPedidoRepository itemRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	public Pedido save(PedidoDTO dto) throws RulesException {
		Cliente cliente = clienteService.findById(dto.getId_cliente());
		
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setTotal(0.0);
		
		return repository.save(pedido);
	}
	
	public List<Pedido> list() throws RulesException {
		return repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	public Pedido findById(Long id) throws RulesException {
		return validarPedido(id);
	}
	
	public void increaseTotal(Long id, Double totalItem) throws RulesException {
		Pedido pedido = validarPedido(id);
		Double total = pedido.getTotal() + totalItem;
		
		pedido.setTotal(total);
		repository.save(pedido);
	}
	
	public void decreaseTotal(Long id, Double totalItem) throws RulesException {
		Pedido pedido = validarPedido(id);
		Double total = pedido.getTotal() - totalItem;
		
		pedido.setTotal(total);
		repository.save(pedido);
	}
	
	public void delete(Long id) throws RulesException {
		Pedido pedido = validarPedido(id);
		repository.delete(pedido);
		
	}
	
	private Pedido validarPedido(Long id) throws RulesException {
		Optional<Pedido> pedido = repository.findById(id);
		if (!pedido.isPresent())
			throw new RulesException("Pedido não encontrado.");
		
		return pedido.get();
	}
}
