package com.eduardooliveiradev.mscartoes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eduardooliveiradev.mscartoes.domain.Cartao;

import br.com.eduardo.spring.arquitetura.arquiteturaspring.service.ICrudService;

@Service
public interface CartaoService extends ICrudService<Cartao> {

	List<Cartao> getCartoesRendaMenorIgual(Long renda);

}
