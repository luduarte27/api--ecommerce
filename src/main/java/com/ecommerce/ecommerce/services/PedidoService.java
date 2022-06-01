package com.ecommerce.ecommerce.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.ecommerce.model.Pedido;
import com.ecommerce.ecommerce.repository.ClienteRepository;
import com.ecommerce.ecommerce.repository.PedidoRepository;
import com.ecommerce.ecommerce.shared.PedidoDTO;
import com.ecommerce.ecommerce.shared.RelatorioPedidoDTO;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public PedidoDTO mapToDTO(Pedido pedido, PedidoDTO pedidoDto) {
		
		pedidoDto.setDataEmissao(pedido.getDataEmissao());
		pedidoDto.setIdCliente(pedido.getCliente().getId());
		pedidoDto.setNumeroPedido(pedido.getNumeroPedido());
		pedidoDto.setValorTotal(pedido.getValorTotal());
		pedidoDto.setId(pedido.getId());
		pedidoDto.setStatus(pedido.getStatus());
		
		return pedidoDto;	
	}
	
	public Pedido mapToModel(Pedido pedido, PedidoDTO pedidoDto) {
		pedido.setDataEmissao(pedidoDto.getDataEmissao());
		pedido.setCliente(clienteRepository.findById(pedidoDto.getIdCliente()).get());
		pedido.setNumeroPedido(pedidoDto.getNumeroPedido());
		pedido.setValorTotal(pedidoDto.getValorTotal());
		pedido.setStatus(pedidoDto.getStatus());
		return pedido;
	}
	
	public void salvar(PedidoDTO pedidoDto) {
		Pedido pedido = new Pedido();
		mapToModel(pedido, pedidoDto);
		pedidoRepository.save(pedido);	
	}
	
	public PedidoDTO buscarPorId(Integer idPedido) {
		return pedidoRepository.findById(idPedido)
				.map(pedido -> mapToDTO(pedido, new PedidoDTO()))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	public List<PedidoDTO> buscarTodos() {
		return pedidoRepository.findAll()
			.stream()
			.map(pedido -> mapToDTO(pedido, new PedidoDTO()))
			.collect(Collectors.toList());
	}

	public void atualizar(Integer idPedido, PedidoDTO pedidoDto) {
		Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
		Pedido pedidoBanco = new Pedido();
		if (pedido.isPresent()) {
			pedidoBanco = pedido.get();
			
			if(pedidoDto.getDataEmissao() != null) {
				pedidoBanco.setDataEmissao(pedidoDto.getDataEmissao());
			}
			if(pedidoDto.getNumeroPedido() != null) {
				pedidoBanco.setNumeroPedido(pedidoDto.getNumeroPedido());
			}
			if(pedidoDto.getValorTotal() != null) {
				pedidoBanco.setValorTotal(pedidoDto.getValorTotal());
			}
			if(pedidoDto.getIdCliente() != null) {
				pedidoBanco.setCliente(clienteRepository.findById(pedidoDto.getIdCliente()).get());
			}
			if(pedidoDto.getStatus() != null) {
				pedidoBanco.setStatus(pedidoDto.getStatus());
			}
			pedidoRepository.save(pedidoBanco);
		}
	}
	
	public void deletar(Integer idPedido) {
		pedidoRepository.deleteById(idPedido);
	}
	
	public RelatorioPedidoDTO relatorio(Integer numeroPedido) {
		return pedidoRepository.listarRelatorio(numeroPedido);
	}
	
}
