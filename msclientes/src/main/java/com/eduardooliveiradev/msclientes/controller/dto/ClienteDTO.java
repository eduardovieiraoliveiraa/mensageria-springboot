package com.eduardooliveiradev.msclientes.controller.dto;

import java.io.Serializable;

import com.eduardooliveiradev.msclientes.controller.ClienteController;

import br.com.eduardo.spring.arquitetura.arquiteturaspring.abstractUtil.AbstractHateOASDTO;
import br.com.eduardo.spring.arquitetura.arquiteturaspring.annotation.ShowLinkHateoas;

@ShowLinkHateoas
public class ClienteDTO extends AbstractHateOASDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String nome;

	private Integer idade;

	private String cpf;

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Class<?> getController() {
		return ClienteController.class;
	}
}
