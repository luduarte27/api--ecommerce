package com.ecommerce.ecommerce.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.ecommerce.model.PedidoItem;
import com.ecommerce.ecommerce.repository.PedidoItemRepository;
import com.ecommerce.ecommerce.repository.PedidoRepository;
import com.ecommerce.ecommerce.repository.ProdutoRepository;
import com.ecommerce.ecommerce.shared.PedidoItemDTO;

@Service
public class PedidoItemService {

	@Autowired
	PedidoItemRepository pedidoItemRepository;

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	public PedidoItemDTO mapToDTO(PedidoItem pedidoItem, PedidoItemDTO pedidoItemDTO) {

		pedidoItemDTO.setIdPedidoItem(pedidoItem.getId());
		pedidoItemDTO.setIdPedido(pedidoItem.getPedido().getId());
		pedidoItemDTO.setIdProduto(pedidoItem.getProduto().getId());
		pedidoItemDTO.setQuantidade(pedidoItem.getQuantidade());
		pedidoItemDTO.setPreco_de_venda(pedidoItem.getPreco_de_venda());
		pedidoItemDTO.setSubtotal(pedidoItem.getSubtotal());

		return pedidoItemDTO;
	}

	public PedidoItem mapToModel(PedidoItem pedidoItem, PedidoItemDTO pedidoItemDTO) {

		pedidoItem.setPedido(pedidoRepository.findById(pedidoItemDTO.getIdPedido()).get());
		pedidoItem.setProduto(produtoRepository.findById(pedidoItemDTO.getIdProduto()).get());
		pedidoItem.setQuantidade(pedidoItemDTO.getQuantidade());
		pedidoItem.setPreco_de_venda(pedidoItemDTO.getPreco_de_venda());
		pedidoItem.setSubtotal(pedidoItemDTO.getSubtotal());

		return pedidoItem;
	}

	public void salvar(PedidoItemDTO pedidoItemDto) {
		PedidoItem pedidoItem = new PedidoItem();
		mapToModel(pedidoItem, pedidoItemDto);
		pedidoItemRepository.save(pedidoItem);
	}

	public PedidoItemDTO buscarPorId(Integer idPedidoItem) {
		return pedidoItemRepository.findById(idPedidoItem).map(pedidoItem -> mapToDTO(pedidoItem, new PedidoItemDTO()))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	public List<PedidoItemDTO> buscarTodos() {
		return pedidoItemRepository.findAll().stream().map(pedidoItem -> mapToDTO(pedidoItem, new PedidoItemDTO()))
				.collect(Collectors.toList());
	}

	public void atualizar(Integer idPedidoItem, PedidoItemDTO pedidoItemDto) {
		Optional<PedidoItem> pedidoItem = pedidoItemRepository.findById(idPedidoItem);
		PedidoItem pedidoItemBanco = new PedidoItem();
		if (pedidoItem.isPresent()) {
			pedidoItemBanco = pedidoItem.get();

			if (pedidoItemDto.getIdPedido() != null) {
				pedidoItemBanco.setPedido(pedidoRepository.findById(pedidoItemDto.getIdPedido()).get());
			}
			if (pedidoItemDto.getIdProduto() != null) {
				pedidoItemBanco.setProduto(produtoRepository.findById(pedidoItemDto.getIdProduto()).get());
			}
			if (pedidoItemDto.getQuantidade() != null) {
				pedidoItemBanco.setQuantidade(pedidoItemDto.getQuantidade());
			}
			pedidoItemRepository.save(pedidoItemBanco);
		}
	}

	public void deletar(Integer idPedidoItem) {
		pedidoItemRepository.deleteById(idPedidoItem);
	}

}
