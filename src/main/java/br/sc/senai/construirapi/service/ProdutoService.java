package br.sc.senai.construirapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.sc.senai.construirapi.exception.RulesException;
import br.sc.senai.construirapi.model.entity.Produto;
import br.sc.senai.construirapi.model.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	public Produto save(Produto prod) throws RulesException {
		Produto produto = new Produto();
		produto.setNome(prod.getNome());
		produto.setValor(prod.getValor());
		produto.setEstoque(prod.getEstoque());
		
		return repository.save(produto);
	}
	
	public List<Produto> list() throws RulesException {
		return repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	}
	
	public Produto findById(Long id) throws RulesException {
		return validarProduto(id);
	}
	
	public Produto update(Long id, Produto prod) throws RulesException {
		Produto produto = validarProduto(id);
		produto.setNome(prod.getNome());
		produto.setEstoque(prod.getEstoque());
		produto.setValor(prod.getValor());
		
		return repository.save(produto);
	}
	
	public void decrementStock(Long id, Integer qtde) throws RulesException {
		Produto produto = validarProduto(id);
		produto.setEstoque(produto.getEstoque() - qtde);
	}
	
	public void incrementStock(Long id, Integer qtde) throws RulesException {
		Produto produto = validarProduto(id);
		produto.setEstoque(produto.getEstoque() + qtde);
	}
	
	public void delete(Long id) throws RulesException {
		Produto produto = validarProduto(id);
		repository.delete(produto);
	}
	
	private Produto validarProduto(Long id) throws RulesException {
		Optional<Produto> produto = repository.findById(id);
		if (!produto.isPresent())
			throw new RulesException("Produto não encontrado.");
		
		return produto.get();
	}
}
