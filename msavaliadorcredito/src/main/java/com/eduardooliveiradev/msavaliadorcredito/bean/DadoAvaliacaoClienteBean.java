package com.eduardooliveiradev.msavaliadorcredito.bean;

import java.math.BigDecimal;

public class DadoAvaliacaoClienteBean {

	private String cpf;

	private BigDecimal renda;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public BigDecimal getRenda() {
		return renda;
	}

	public void setRenda(BigDecimal renda) {
		this.renda = renda;
	}
}
