package com.ecommerce.ecommerce.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ecommerce.ecommerce.model.Produto;
import com.ecommerce.ecommerce.model.exception.ResourceNotFoundException;
import com.ecommerce.ecommerce.repository.ProdutoRepository;
import com.ecommerce.ecommerce.shared.ProdutoDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> obterTodos(){
        
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
        .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
        .collect(Collectors.toList());
    }

    public Optional<ProdutoDTO> obterPorId(Integer id){

        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Produto com id " + id + " não encontrado.");
        }

        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);

        return Optional.of(dto);
    }

    public ProdutoDTO adicionar(ProdutoDTO produtoDTO) {
        produtoDTO.setId(null);

        ModelMapper mapper = new ModelMapper();

        Produto produto = mapper.map(produtoDTO, Produto.class);

        produto = produtoRepository.save(produto);

        produtoDTO.setId(produto.getId());

        return produtoDTO;
    }

    public void deletar(Integer id){
        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível deletar o produto com o id: " + id);
        }

        produtoRepository.deleteById(id);
    }

    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDTO){
        produtoDTO.setId(id);

        ModelMapper mapper = new ModelMapper();

        Produto produto = mapper.map(produtoDTO, Produto.class);

        produtoRepository.save(produto);

        return produtoDTO;
    }
}
