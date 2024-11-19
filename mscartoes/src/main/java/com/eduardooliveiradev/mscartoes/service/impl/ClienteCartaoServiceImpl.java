package com.eduardooliveiradev.mscartoes.service.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.eduardooliveiradev.mscartoes.domain.ClienteCartao;
import com.eduardooliveiradev.mscartoes.repository.ClienteCartaoRepository;
import com.eduardooliveiradev.mscartoes.service.ClienteCartaoService;

import br.com.eduardo.spring.arquitetura.arquiteturaspring.service.CrudServiceImpl;

@Service
public class ClienteCartaoServiceImpl extends CrudServiceImpl<ClienteCartao> implements ClienteCartaoService {

	private ClienteCartaoRepository clienteCartaoRepository;

	public ClienteCartaoServiceImpl(ClienteCartaoRepository clienteCartaoRepository) {
		this.clienteCartaoRepository = clienteCartaoRepository;
	}
	
	@Override
	public List<ClienteCartao> findByCpf(String cpf){
		return clienteCartaoRepository.findByCpf(cpf);
	}

	@Override
	public JpaRepository<ClienteCartao, Long> getRepository() {
		return clienteCartaoRepository;
	}

}
