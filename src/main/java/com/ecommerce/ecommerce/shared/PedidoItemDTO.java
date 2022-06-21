package com.ecommerce.ecommerce.shared;

import java.io.Serializable;

public class PedidoItemDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idPedidoItem;
    private Integer quantidade;
    private Integer idPedido;
    private Integer idProduto;
    private Double preco_de_venda;
    private Double subtotal;

    
    public PedidoItemDTO() {}


	public Integer getIdPedidoItem() {
		return idPedidoItem;
	}


	public void setIdPedidoItem(Integer idPedidoItem) {
		this.idPedidoItem = idPedidoItem;
	}


	public Integer getQuantidade() {
		return quantidade;
	}


	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}


	public Integer getIdPedido() {
		return idPedido;
	}


	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}


	public Integer getIdProduto() {
		return idProduto;
	}


	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}


	public Double getPreco_de_venda() {
		return preco_de_venda;
	}


	public void setPreco_de_venda(Double preco_de_venda) {
		this.preco_de_venda = preco_de_venda;
	}


	public Double getSubtotal() {
		return subtotal;
	}


	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
    

}
