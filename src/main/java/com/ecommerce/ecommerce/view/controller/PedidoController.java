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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.services.PedidoService;
import com.ecommerce.ecommerce.shared.PedidoDTO;
import com.ecommerce.ecommerce.shared.RelatorioPedidoDTO;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@PostMapping("/salvar")
	public ResponseEntity<Void> salvar(@RequestBody PedidoDTO pedidoDto){
		pedidoService.salvar(pedidoDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/buscar/{idPedido}")
	public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable Integer idPedido){
		return ResponseEntity.ok(pedidoService.buscarPorId(idPedido));
	}
	
	@GetMapping("/lista")
	public ResponseEntity<List<PedidoDTO>> listarTodos(){
		return ResponseEntity.ok(pedidoService.buscarTodos());
	}
	
	@PutMapping("/atualizar/{idPedido}")
	public ResponseEntity<Void> atualizar(@PathVariable Integer idPedido ,@RequestBody PedidoDTO pedidoDto){
		pedidoService.atualizar(idPedido,pedidoDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@DeleteMapping("/{idPedido}")
	public ResponseEntity<Void> deletar(@PathVariable Integer idPedido){
		pedidoService.deletar(idPedido);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/relatorio")
	public ResponseEntity<RelatorioPedidoDTO> relatorio(@RequestParam Integer numeroPedido){
		return ResponseEntity.ok(pedidoService.relatorio(numeroPedido));
	}
}
