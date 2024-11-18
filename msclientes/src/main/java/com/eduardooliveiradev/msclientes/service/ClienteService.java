package com.eduardooliveiradev.msclientes.service;

import org.springframework.stereotype.Service;

import com.eduardooliveiradev.msclientes.domain.Cliente;
import com.google.common.base.Optional;

import br.com.eduardo.spring.arquitetura.arquiteturaspring.service.ICrudService;

@Service
public interface ClienteService extends ICrudService<Cliente> {

	Optional<Cliente> getByCpf(String cpf);
}
