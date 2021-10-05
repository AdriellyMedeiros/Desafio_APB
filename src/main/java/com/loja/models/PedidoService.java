package com.loja.models;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loja.models.dto.PedidoDTO;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido cadastrar(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	public List<PedidoDTO> getPedidos(){
		return pedidoRepository.findAll().stream().map(PedidoDTO::new).collect(Collectors.toList());
	}
	
	public Optional<Pedido> getPedidoById(Long id) {
		return pedidoRepository.findById(id);
	}
	
	public Pedido atualizar(Pedido pedido, Long id) {
		Optional<Pedido> optional = getPedidoById(id);

		if (optional.isPresent()) {
			Pedido pedidoBD = optional.get();
			pedidoBD.setValorTotal(pedido.getValorTotal());
			pedidoBD.setDataPedido(pedido.getDataPedido());
			pedidoBD.setCliente(pedido.getCliente());
			
			pedidoRepository.save(pedidoBD);
			return pedidoBD;
		}
		else {
			throw new RuntimeException("Erro ao atualizar pedido de ID = " + id);
		}
	}
	
	public void remover(Long id) {
		Optional<Pedido> pedido = getPedidoById(id);
		if(pedido.isPresent()) {
			pedidoRepository.deleteById(id);
		}
	}
}