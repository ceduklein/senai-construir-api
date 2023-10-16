package br.sc.senai.construirapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.sc.senai.construirapi.dto.ItemDTO;
import br.sc.senai.construirapi.exception.RulesException;
import br.sc.senai.construirapi.model.entity.ItemPedido;
import br.sc.senai.construirapi.model.entity.Pedido;
import br.sc.senai.construirapi.model.entity.Produto;
import br.sc.senai.construirapi.model.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository repository;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired ProdutoService produtoService;
	
	public ItemPedido save(ItemDTO dto) throws RulesException {
		Pedido pedido = pedidoService.findById(dto.getId_pedido());
		Produto produto = produtoService.findById(dto.getId_produto());
		
		ItemPedido item = new ItemPedido();
		item.setProduto(produto);
		item.setPedido(pedido);
		item.setQtde(dto.getQtde());
		item.setTotalItem(produto.getValor() * dto.getQtde());
		
		ItemPedido itemSalvo = repository.save(item);
		pedidoService.increaseTotal(itemSalvo.getPedido().getId(), itemSalvo.getTotalItem());
		produtoService.decreaseStock(itemSalvo.getProduto().getId(), itemSalvo.getQtde());
		
		return itemSalvo;
	}
	
	public ItemPedido findById(Long id) throws RulesException {
		return validarItem(id);
	}
	
	public List<ItemPedido> findByIdPedido(Long id_pedido) throws RulesException {
		Pedido pedido = pedidoService.findById(id_pedido);
		return repository.findByPedido(pedido);
	}
	
	public void delete(Long id) throws RulesException {
		ItemPedido item = validarItem(id);
		Pedido pedido = pedidoService.findById(item.getPedido().getId());
		Produto produto = produtoService.findById(item.getProduto().getId());
		
		repository.delete(item);
		
		pedidoService.decreaseTotal(pedido.getId(), item.getTotalItem());
		produtoService.increaseStock(produto.getId(), item.getQtde());
	}
	
	public void deleteByIdPedido(Long id_pedido) throws RulesException {
		List<ItemPedido> itens =  findByIdPedido(id_pedido);
		
		itens.forEach(item -> {
			try {
				delete(item.getId());
			} catch (RulesException e) {
				e.printStackTrace();
			}
		});
		
		pedidoService.delete(id_pedido);
	}
	
	private ItemPedido validarItem(Long id) throws RulesException {
		Optional<ItemPedido> item = repository.findById(id);
		if(!item.isPresent())
			throw new RulesException("item não encontrado.");
		
		return item.get();
	}
}
