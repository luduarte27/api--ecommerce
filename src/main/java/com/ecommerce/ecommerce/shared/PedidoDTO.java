package com.ecommerce.ecommerce.shared;

import java.io.Serializable;
import java.util.Date;

import com.ecommerce.ecommerce.model.Status;

public class PedidoDTO implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private Integer id;
    private Double valorTotal;
    private Integer numeroPedido;
    private Date dataEmissao;
    private Integer idCliente;
    private Status status;
    
    public PedidoDTO() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Integer getNumeroPedido() {
		return numeroPedido;
	}

	public void setNumeroPedido(Integer numeroPedido) {
		this.numeroPedido = numeroPedido;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}


    
    

}
