package com.eduardooliveiradev.msclientes.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.eduardooliveiradev.msclientes.domain.Cliente;
import com.eduardooliveiradev.msclientes.repository.ClienteRepository;
import com.eduardooliveiradev.msclientes.service.ClienteService;
import com.google.common.base.Optional;

import br.com.eduardo.spring.arquitetura.arquiteturaspring.service.CrudServiceImpl;

@Service
public class ClienteServiceImpl extends CrudServiceImpl<Cliente> implements ClienteService {
	
	private final ClienteRepository clienteRepository;

	public ClienteServiceImpl(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Override
	public Optional<Cliente> getByCpf(String cpf){
		return clienteRepository.findByCpf(cpf);
	}
	
	@Override
	public Cliente save(Cliente entity) {
		Optional<Cliente> optionalCliente = getByCpf(entity.getCpf());
		
		if(optionalCliente.isPresent()) throw new RuntimeException("Já existe um cliente cadastrado com esse CPF.");
		
		return super.save(entity);
	}

	@Override
	public JpaRepository<Cliente, Long> getRepository() {
		return clienteRepository;
	}
}
