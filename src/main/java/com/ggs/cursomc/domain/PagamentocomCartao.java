package com.ggs.cursomc.domain;

import javax.persistence.Entity;

import com.ggs.cursomc.domain.enums.EstadoPagamento;

@Entity
public class PagamentocomCartao extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	private Integer numeroDeParcelas;
	
	public PagamentocomCartao() {
		
	}

	public PagamentocomCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
		super(id, estado, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
		// TODO Auto-generated constructor stub
	}

	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}

	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	

}
