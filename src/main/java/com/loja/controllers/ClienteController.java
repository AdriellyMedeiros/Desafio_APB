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

import com.loja.models.Cliente;
import com.loja.models.ClienteService;
import com.loja.models.dto.ClienteDTO;

@RestController
@RequestMapping("/api/loja/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	

	@PostMapping
	public String cadastrarCliente(@RequestBody Cliente cliente) {
		Cliente c = service.cadastrar(cliente);
		return "Cliente cadastrado com sucesso: " + c.getId();
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> get() {
		return ResponseEntity.ok(service.getClientes());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> get(@PathVariable("id") Long id) {
		Optional<Cliente> cliente = service.getClienteById(id);
		return cliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		
	}
	
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<Cliente> get(@PathVariable("cpf") String cpf) {
		Optional<Cliente> cliente = service.getClienteByCpf(cpf);
		return cliente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		
	}
	
	@PutMapping("/{id}")
	public String atualizarCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
		Cliente c = service.atualizar(cliente, id);
		return "Aluno atualizado com sucesso: " + c.getId();
	}
	
	@DeleteMapping("/{id}")
	public String removerCliente(@PathVariable("id") Long id) {
		service.remover(id);
		return "Cliente removido com sucesso.";
	}
}
