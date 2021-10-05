package com.loja.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loja.models.Pedido;
import com.loja.models.PedidoService;
import com.loja.models.dto.PedidoDTO;



@RestController
@RequestMapping("/api/loja/pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	

	@PostMapping
	public String cadastrarCliente(@RequestBody Pedido pedido) {
		Pedido p = service.cadastrar(pedido);
		return "Pedido cadastrado com sucesso: " + p.getId();
	}
	
	@GetMapping
	public ResponseEntity<List<PedidoDTO>> get() {
		return ResponseEntity.ok(service.getPedidos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> get(@PathVariable("id") Long id) {
		Optional<Pedido> pedido = service.getPedidoById(id);
		return pedido.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		
	}
	
	@PutMapping("/{id}")
	public String atualizarPedido(@PathVariable("id") Long id, @RequestBody Pedido pedido) {
		Pedido p = service.atualizar(pedido, id);
		return "Pedido atualizado com sucesso: " + p.getId();
	}
	
	@DeleteMapping("/{id}")
	public String removerPedido(@PathVariable("id") Long id) {
		service.remover(id);
		return "Pedido removido com sucesso.";
	}
}