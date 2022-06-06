package com.ecommerce.ecommerce.view.controller;

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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ecommerce.ecommerce.services.CategoriaService;
import com.ecommerce.ecommerce.shared.CategoriaDTO;
import com.ecommerce.ecommerce.view.model.categoria.CategoriaRequest;
import com.ecommerce.ecommerce.view.model.categoria.CategoriaResponse;


@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;
    
    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> obterTodos(){
        List<CategoriaDTO> categorias = categoriaService.obterTodos();

        List<CategoriaResponse> response = categorias.stream()
        .map(categoria -> new ModelMapper().map(categoria, CategoriaResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> obterPorId(@PathVariable Integer id){
        Optional<CategoriaDTO> dto =  categoriaService.obterPorId(id);

        CategoriaResponse categoria = new ModelMapper().map(dto.get(), CategoriaResponse.class);
        
        return new ResponseEntity<>(categoria, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> adicionar(@RequestBody CategoriaRequest categoriaReq){
        ModelMapper mapper = new ModelMapper();

        CategoriaDTO categoriaDTO = mapper.map(categoriaReq, CategoriaDTO.class);

        categoriaDTO = categoriaService.adicionar(categoriaDTO);

        return new ResponseEntity<>(mapper.map(categoriaDTO, CategoriaResponse.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        categoriaService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> atualizar(@RequestBody CategoriaRequest categoriaReq, @PathVariable Integer id){

        ModelMapper mapper = new ModelMapper();

        CategoriaDTO categoriaDTO = mapper.map(categoriaReq, CategoriaDTO.class);

        categoriaDTO = categoriaService.atualizar(id, categoriaDTO);

        return new ResponseEntity<>(
            mapper.map(categoriaDTO, CategoriaResponse.class),
            HttpStatus.OK
        );
    }
}
