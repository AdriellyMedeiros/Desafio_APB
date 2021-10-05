package com.loja.models;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.models.dto.EnderecoDTO;


@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Endereco cadastrar(Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	
	public List<EnderecoDTO> getEnderecos(){
		return enderecoRepository.findAll().stream().map(EnderecoDTO::new).collect(Collectors.toList());
	}
	
	public Optional<Endereco> getEnderecoById(Long id) {
		return enderecoRepository.findById(id);
	}
	
	public Endereco atualizar(Endereco endereco, Long id) {
		Optional<Endereco> optional = getEnderecoById(id);

		if (optional.isPresent()) {
			Endereco enderecoBD = optional.get();
			enderecoBD.setRua(endereco.getRua());
			enderecoBD.setNumero(endereco.getNumero());
			enderecoBD.setBairro(endereco.getBairro());
			enderecoBD.setCep(endereco.getCep());
			
			enderecoRepository.save(enderecoBD);
			return enderecoBD;
		}
		else {
			throw new RuntimeException("Erro ao atualizar endereco de ID = " + id);
		}
	}
	
	public void remover(Long id) {
		Optional<Endereco> endereco = getEnderecoById(id);
		if(endereco.isPresent()) {
			enderecoRepository.deleteById(id);
		}
	}
}