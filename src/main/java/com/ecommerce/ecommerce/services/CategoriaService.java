package com.ecommerce.ecommerce.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.ecommerce.ecommerce.model.Categoria;
import com.ecommerce.ecommerce.model.exception.ResourceNotFoundException;
import com.ecommerce.ecommerce.repository.CategoriaRepository;
import com.ecommerce.ecommerce.shared.CategoriaDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> obterTodos(){
        
        List<Categoria> categorias = categoriaRepository.findAll();

        return categorias.stream()
        .map(categoria -> new ModelMapper().map(categoria, CategoriaDTO.class))
        .collect(Collectors.toList());
    }

    public CategoriaDTO adicionar(CategoriaDTO categoriaDTO){
        categoriaDTO.setId(null);

        ModelMapper mapper = new ModelMapper();

        Categoria categoria = mapper.map(categoriaDTO, Categoria.class);

        categoria = categoriaRepository.save(categoria);

        categoriaDTO.setId(categoria.getId());

        return categoriaDTO;
    }

    public void deletar(Integer id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if(categoria.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível deletar a categoria com o id: " + id);
        }
        categoriaRepository.deleteById(id);
    }

    public CategoriaDTO atualizar(Integer id, CategoriaDTO categoriaDTO){
        
        categoriaDTO.setId(id);

        
        ModelMapper mapper = new ModelMapper();

        
        Categoria categoria = mapper.map(categoriaDTO, Categoria.class);
 
        categoriaRepository.save(categoria);

        return categoriaDTO;
    }
    
}
