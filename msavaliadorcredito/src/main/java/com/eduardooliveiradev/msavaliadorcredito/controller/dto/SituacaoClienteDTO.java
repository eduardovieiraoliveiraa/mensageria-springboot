package com.eduardooliveiradev.msavaliadorcredito.controller.dto;

import java.io.Serializable;
import java.util.List;

public class SituacaoClienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private ClienteDTO cliente;

	private List<ClienteCartaoDTO> cartoes;

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public List<ClienteCartaoDTO> getCartoes() {
		return cartoes;
	}

	public void setCartoes(List<ClienteCartaoDTO> cartoes) {
		this.cartoes = cartoes;
	}
}