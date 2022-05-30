package com.ecommerce.ecommerce.view.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.ecommerce.services.ClienteService;
import com.ecommerce.ecommerce.shared.ClienteDTO;
import com.ecommerce.ecommerce.view.model.cliente.ClienteRequest;
import com.ecommerce.ecommerce.view.model.cliente.ClienteResponse;
import com.ecommerce.ecommerce.view.model.produto.ProdutoResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> obterTodos(){
        
        List<ClienteDTO> clientes = clienteService.obterTodos();

        List<ClienteResponse> response = clientes.stream()
        .map(cliente -> new ModelMapper().map(cliente, ClienteResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // @PostMapping
    // public ResponseEntity<ClienteResponse> adicionar(@RequestBody ClienteRequest clienteReq){
        
    //     ModelMapper mapper = new ModelMapper();

        

        // ClienteDTO clienteDTO = mapper.map(clienteReq, ProdutoDTO.class);

        // produtoDTO = produtoService.adicionar(produtoDTO);

        // return new ResponseEntity<>(mapper.map(ClienteDTO, ProdutoResponse.class), HttpStatus.CREATED);
    // }

    @DeleteMapping
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        clienteService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
