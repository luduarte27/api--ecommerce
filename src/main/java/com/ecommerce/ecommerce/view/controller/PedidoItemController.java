package com.ecommerce.ecommerce.view.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.services.PedidoItemService;
import com.ecommerce.ecommerce.shared.PedidoItemDTO;

@RestController
@RequestMapping("/pedidoitem")
public class PedidoItemController {
	
	@Autowired
	private PedidoItemService pedidoItemService;
	
	@PostMapping("/salvar")
	public ResponseEntity<Void> salvar(@RequestBody PedidoItemDTO pedidoItemDto){
		pedidoItemService.salvar(pedidoItemDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/buscar/{idPedidoItem}")
	public ResponseEntity<PedidoItemDTO> buscarPorId(@PathVariable Integer idPedidoItem){
		return ResponseEntity.ok(pedidoItemService.buscarPorId(idPedidoItem));
	}
	
	@GetMapping("/lista")
	public ResponseEntity<List<PedidoItemDTO>> listarTodos(){
		return ResponseEntity.ok(pedidoItemService.buscarTodos());
	}
	
	@PutMapping("/atualizar/{idPedidoItem}")
	public ResponseEntity<Void> atualizar(@PathVariable Integer idPedidoItem ,@RequestBody PedidoItemDTO pedidoItemDto){
		pedidoItemService.atualizar(idPedidoItem,pedidoItemDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@DeleteMapping("/{idPedidoItem}")
	public ResponseEntity<Void> deletar(@PathVariable Integer idPedidoItem){
		pedidoItemService.deletar(idPedidoItem);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
}
