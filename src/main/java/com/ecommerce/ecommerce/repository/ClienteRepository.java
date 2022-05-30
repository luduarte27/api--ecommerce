package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
    
}
