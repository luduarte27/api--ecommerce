package com.ecommerce.ecommerce.repository;

import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.model.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{
    
}
