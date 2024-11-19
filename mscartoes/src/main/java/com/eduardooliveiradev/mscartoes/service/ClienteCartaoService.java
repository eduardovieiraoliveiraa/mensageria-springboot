package com.eduardooliveiradev.mscartoes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eduardooliveiradev.mscartoes.domain.ClienteCartao;

import br.com.eduardo.spring.arquitetura.arquiteturaspring.service.ICrudService;

@Service
public interface ClienteCartaoService extends ICrudService<ClienteCartao>{

	List<ClienteCartao> findByCpf(String cpf);

}
