package com.eduardooliveiradev.msavaliadorcredito.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.eduardooliveiradev.msavaliadorcredito.clients.ClienteResourceClient;
import com.eduardooliveiradev.msavaliadorcredito.controller.dto.ClienteDTO;
import com.eduardooliveiradev.msavaliadorcredito.controller.dto.SituacaoClienteDTO;

@Service
public class AvaliadorCreditoService {
	
	@Autowired
	private ClienteResourceClient clienteResourceClient;
	
	public SituacaoClienteDTO consultarSituacaoCliente(@PathVariable("cpf") String cpf) {
		SituacaoClienteDTO situacaoClienteDTO = new SituacaoClienteDTO();
		
		ClienteDTO consultarCliente = clienteResourceClient.consultarCliente(cpf);
		situacaoClienteDTO.setCliente(consultarCliente);
		
		return situacaoClienteDTO;
	}
}
