package com.loja.models.dto;

import com.loja.models.Endereco;

import lombok.Data;

@Data
public class EnderecoDTO {
	
	private Long id;
	private String rua;
	private String numero;
	private String bairro;
	private String cep;
	
	public EnderecoDTO(Endereco e) {
		this.id = e.getId();
		this.rua = e.getRua();
		this.numero = e.getNumero();
		this.bairro = e.getBairro();
		this.cep = e.getCep();
	}
}
