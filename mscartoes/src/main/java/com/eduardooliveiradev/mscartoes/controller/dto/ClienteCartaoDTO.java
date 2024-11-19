package com.eduardooliveiradev.mscartoes.controller.dto;

import java.math.BigDecimal;

import br.com.eduardo.spring.arquitetura.arquiteturaspring.abstractUtil.AbstractDTO;

public class ClienteCartaoDTO extends AbstractDTO {

	private Long id;

	private String cpf;

	private CartaoDTO cartao;

	private BigDecimal limite;

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

	@Override
	public Long getId() {
		return id;
	}

}
