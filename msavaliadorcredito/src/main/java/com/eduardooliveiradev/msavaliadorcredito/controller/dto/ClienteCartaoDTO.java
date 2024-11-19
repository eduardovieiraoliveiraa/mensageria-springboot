package com.eduardooliveiradev.msavaliadorcredito.controller.dto;

import java.math.BigDecimal;

public class ClienteCartaoDTO {

	private Long id;

	private String cpf;

	private CartaoDTO cartao;

	private BigDecimal limite;

	public Long getId() {
		return id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public CartaoDTO getCartao() {
		return cartao;
	}

	public void setCartao(CartaoDTO cartao) {
		this.cartao = cartao;
	}

	public BigDecimal getLimite() {
		return limite;
	}

	public void setLimite(BigDecimal limite) {
		this.limite = limite;
	}

	public void setId(Long id) {
		this.id = id;
	}
}