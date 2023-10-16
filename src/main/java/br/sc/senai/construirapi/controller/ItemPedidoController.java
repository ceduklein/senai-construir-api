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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.sc.senai.construirapi.dto.ItemDTO;
import br.sc.senai.construirapi.model.entity.ItemPedido;
import br.sc.senai.construirapi.service.ItemPedidoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/itens")
public class ItemPedidoController {

	@Autowired
	private ItemPedidoService service;
	
	@PostMapping()
	public ResponseEntity<?> save(@RequestBody ItemDTO dto) {
		try {
			ItemPedido item = service.save(dto);
			return new ResponseEntity<>(item, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping()
	public ResponseEntity<?> list(@RequestParam Long id_pedido) {
		try {
			List<ItemPedido> itens = service.findByIdPedido(id_pedido);
			return new ResponseEntity<>(itens, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		try {
			ItemPedido item = service.findById(id);
			return new ResponseEntity<>(item, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			service.delete(id);
			return new ResponseEntity<>("Item excluído.", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/pedido/{id}")
	public ResponseEntity<?> deletePedido(@PathVariable("id") Long id) {
		try {
			service.deleteByIdPedido(id);
			return new ResponseEntity<>("Pedido excluído.", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
