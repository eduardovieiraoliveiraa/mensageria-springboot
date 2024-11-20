package com.eduardooliveiradev.msavaliadorcredito.controller.dto;

import java.util.List;

import com.eduardooliveiradev.msavaliadorcredito.bean.CartaoAprovadoBean;

public class RetornoAvaliacaoClienteDTO {

	private List<CartaoAprovadoBean> cartoesAprovados;

	public List<CartaoAprovadoBean> getCartoesAprovados() {
		return cartoesAprovados;
	}

	public void setCartoesAprovados(List<CartaoAprovadoBean> cartoesAprovados) {
		this.cartoesAprovados = cartoesAprovados;
	}
}
