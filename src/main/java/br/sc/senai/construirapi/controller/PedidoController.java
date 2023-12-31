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
import org.springframework.web.bind.annotation.RestController;

import br.sc.senai.construirapi.dto.PedidoDTO;
import br.sc.senai.construirapi.model.entity.Pedido;
import br.sc.senai.construirapi.service.PedidoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService service;
	
	@PostMapping()
	public ResponseEntity<?> save(@RequestBody PedidoDTO dto) {
		try {
			Pedido pedido = service.save(dto);
			return new ResponseEntity<>(pedido, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping()
	public ResponseEntity<?> list() {
		try {
			List<Pedido> pedidos = service.list();
			return new ResponseEntity<>(pedidos, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		try {
			Pedido pedido = service.findById(id);
			return new ResponseEntity<>(pedido, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			service.delete(id);
			return new ResponseEntity<>("Pedido excluído.", HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
