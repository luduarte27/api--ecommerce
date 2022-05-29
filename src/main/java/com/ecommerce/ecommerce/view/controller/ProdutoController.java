package com.ecommerce.ecommerce.view.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.ecommerce.ecommerce.services.ProdutoService;
import com.ecommerce.ecommerce.shared.ProdutoDTO;
import com.ecommerce.ecommerce.view.model.produto.ProdutoRequest;
import com.ecommerce.ecommerce.view.model.produto.ProdutoResponse;

import org.modelmapper.ModelMapper;
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

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
    
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> obterTodos(){

        List<ProdutoDTO> produtos = produtoService.obterTodos();

        List<ProdutoResponse> response = produtos.stream()
        .map(produto -> new ModelMapper().map(produto, ProdutoResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> adicionar(@RequestBody ProdutoRequest produtoReq){
        ModelMapper mapper = new ModelMapper();

        ProdutoDTO produtoDTO = mapper.map(produtoReq, ProdutoDTO.class);

        produtoDTO = produtoService.adicionar(produtoDTO);

        return new ResponseEntity<>(mapper.map(produtoDTO, ProdutoResponse.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        produtoService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@RequestBody ProdutoRequest produtoReq, @PathVariable Integer id){

        ModelMapper mapper = new ModelMapper();

        ProdutoDTO produtoDTO = mapper.map(produtoReq, ProdutoDTO.class);

        produtoDTO = produtoService.atualizar(id, produtoDTO);

        return new ResponseEntity<>(
          mapper.map( produtoDTO, ProdutoResponse.class),
          HttpStatus.OK  
        );

    }

}
