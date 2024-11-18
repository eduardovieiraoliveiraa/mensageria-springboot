package com.eduardooliveiradev.mscartoes.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.eduardooliveiradev.mscartoes.domain.Cartao;
import com.eduardooliveiradev.mscartoes.repository.CartaoRepository;
import com.eduardooliveiradev.mscartoes.service.CartaoService;

import br.com.eduardo.spring.arquitetura.arquiteturaspring.service.CrudServiceImpl;

@Service
public class CartaoServiceImpl extends CrudServiceImpl<Cartao> implements CartaoService {

	private CartaoRepository cartaoRepository;

	public CartaoServiceImpl(CartaoRepository cartaoRepository) {
		this.cartaoRepository = cartaoRepository;
	}
	
	@Override
	public List<Cartao> getCartoesRendaMenorIgual(Long renda){
		BigDecimal rendaCartao = BigDecimal.valueOf(renda);
		
		return cartaoRepository.findByRendaLessThanEqual(rendaCartao);
	}

	@Override
	public JpaRepository<Cartao, Long> getRepository() {
		return cartaoRepository;
	}
}