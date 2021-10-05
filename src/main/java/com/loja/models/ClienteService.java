package com.loja.models;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.models.dto.ClienteDTO;


@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente cadastrar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public List<ClienteDTO> getClientes(){
		return clienteRepository.findAll().stream().map(ClienteDTO::new).collect(Collectors.toList());
	}
	
	public Optional<Cliente> getClienteById(Long id) {
		return clienteRepository.findById(id);
	}
	
	public Optional<Cliente> getClienteByCpf(String cpf) {
		return clienteRepository.findByCpf(cpf);
	}
	
	public Cliente atualizar(Cliente cliente, Long id) {
		Optional<Cliente> optional = getClienteById(id);

		if (optional.isPresent()) {
			Cliente clienteBD = optional.get();
			clienteBD.setNome(cliente.getNome());
			clienteBD.setCpf(cliente.getCpf());
			clienteBD.setDataNascimento(cliente.getDataNascimento());
			clienteBD.setEndereco(cliente.getEndereco());
			
			clienteRepository.save(clienteBD);
			return clienteBD;
		}
		else {
			throw new RuntimeException("Erro ao atualizar cliente de ID = " + id);
		}
	}
	
	public void remover(Long id) {
		Optional<Cliente> cliente = getClienteById(id);
		if(cliente.isPresent()) {
			clienteRepository.deleteById(id);
		}
	}
}
