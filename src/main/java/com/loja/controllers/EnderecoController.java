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

import com.loja.models.Endereco;
import com.loja.models.EnderecoService;
import com.loja.models.dto.EnderecoDTO;


@RestController
@RequestMapping("/api/loja/enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoService service;
	

	@PostMapping
	public String cadastrarEndereco(@RequestBody Endereco endereco) {
		Endereco e = service.cadastrar(endereco);
		return "Endereco cadastrado com sucesso: " + e.getId();
	}
	
	@GetMapping
	public ResponseEntity<List<EnderecoDTO>> get() {
		return ResponseEntity.ok(service.getEnderecos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Endereco> get(@PathVariable("id") Long id) {
		Optional<Endereco> endereco = service.getEnderecoById(id);
		return endereco.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
		
	}
	
	@PutMapping("/{id}")
	public String atualizarEndereco(@PathVariable("id") Long id, @RequestBody Endereco endereco) {
		Endereco e = service.atualizar(endereco, id);
		return "Endereco atualizado com sucesso: " + e.getId();
	}
	
	@DeleteMapping("/{id}")
	public String removerEndereco(@PathVariable("id") Long id) {
		service.remover(id);
		return "Endereco removido com sucesso.";
	}
}