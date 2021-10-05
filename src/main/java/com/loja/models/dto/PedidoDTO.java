package com.loja.models.dto;

import java.sql.Timestamp;

import com.loja.models.Cliente;
import com.loja.models.Pedido;

import lombok.Data;

@Data
public class PedidoDTO {
	
	private Long id;
	private double valorTotal;
	private Timestamp dataPedido;
	private Cliente cliente;
	
	public PedidoDTO(Pedido p) {
		this.id = p.getId();
		this.valorTotal = p.getValorTotal();
		this.dataPedido = p.getDataPedido();
		this.cliente = p.getCliente();
	}
}
