package com.eduardooliveiradev.mscartoes.controller.dto;

import java.math.BigDecimal;

import br.com.eduardo.spring.arquitetura.arquiteturaspring.abstractUtil.AbstractDTO;

public class CartaoDTO extends AbstractDTO {

	private Long id;

	private String nome;

	private String bandeira;

	private BigDecimal renda;

	private BigDecimal limiteBasico;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public BigDecimal getRenda() {
		return renda;
	}

	public void setRenda(BigDecimal renda) {
		this.renda = renda;
	}

	public BigDecimal getLimiteBasico() {
		return limiteBasico;
	}

	public void setLimiteBasico(BigDecimal limiteBasico) {
		this.limiteBasico = limiteBasico;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {
		return id;
	}
}