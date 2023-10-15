package br.sc.senai.construirapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.sc.senai.construirapi.model.entity.Produto;
import br.sc.senai.construirapi.service.ProdutoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService service;
	
	@PostMapping()
	public ResponseEntity<?> save(@RequestBody Produto prod) {
		try {
			Produto produto = service.save(prod);
			return new ResponseEntity<>(produto, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping()
	public ResponseEntity<?> list() {
		try {
			List<Produto> produtos = service.list();
			return new ResponseEntity<>(produtos, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			Produto produto = service.findById(id);
			return new ResponseEntity<>(produto, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Produto prod) {
		try {
			Produto produto = service.update(id, prod);
			return new ResponseEntity<>(produto, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			service.delete(id);
			return new ResponseEntity<>("Produto excluído", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
