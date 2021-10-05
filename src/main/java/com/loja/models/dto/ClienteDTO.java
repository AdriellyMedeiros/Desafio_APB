package com.loja.models.dto;


import java.sql.Date;

import com.loja.models.Cliente;
import com.loja.models.Endereco;

import lombok.Data;

@Data
public class ClienteDTO {

	private Long id;
	private String nome;
	private String cpf;
	private Date dataNascimento;
	private Endereco endereco;
	
	public ClienteDTO(Cliente c) {
		this.id = c.getId();
		this.nome = c.getNome();
		this.cpf = c.getCpf();
		this.dataNascimento = c.getDataNascimento();
		this.endereco = c.getEndereco();
	}
}
