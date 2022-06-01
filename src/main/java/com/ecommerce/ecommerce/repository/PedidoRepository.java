package com.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.model.Pedido;
import com.ecommerce.ecommerce.shared.RelatorioPedidoDTO;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	
	@Query(value="select  \r\n"
			+ "p.numero_pedido as numeroPedido,\r\n"
			+ "(select p2.nome  from produto p2 where p2.id = pi2.produto_id) as nomeProduto,\r\n"
			+ "sum(p.valor_total) as valorTotal,\r\n"
			+ "sum(pi2.quantidade) as quantidadeProduto\r\n"
			+ "from pedido p\r\n"
			+ "join pedido_item pi2 on \r\n"
			+ "p.id = pi2.pedido_id \r\n"
			+ "where p.numero_pedido = :numeropedido\r\n"
			+ "group by p.numero_pedido , nomeProduto\r\n"
			+ "",nativeQuery=true)
	RelatorioPedidoDTO listarRelatorio(Integer numeropedido); 
	

}
