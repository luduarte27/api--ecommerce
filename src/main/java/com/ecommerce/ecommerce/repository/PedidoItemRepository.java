package com.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.model.PedidoItem;

@Repository
public interface PedidoItemRepository extends JpaRepository<PedidoItem, Integer> {

}
