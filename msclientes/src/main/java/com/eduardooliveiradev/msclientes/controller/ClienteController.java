package com.eduardooliveiradev.msclientes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eduardooliveiradev.msclientes.controller.dto.ClienteDTO;
import com.eduardooliveiradev.msclientes.domain.Cliente;
import com.eduardooliveiradev.msclientes.service.ClienteService;
import com.google.common.base.Optional;

import br.com.eduardo.spring.arquitetura.arquiteturaspring.controller.AbstractController;
import br.com.eduardo.spring.arquitetura.arquiteturaspring.service.ICrudService;

@RestController
@RequestMapping("/clientes")
public class ClienteController extends AbstractController<Cliente, ClienteDTO>{
	
	@Autowired
    private ClienteService clienteService;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/status")
	public String status() {
		String porta = environment.getProperty("local.server.port");
		
		return "ok - server port: " + porta;
	}
	
	@GetMapping("/consultar")
	public ClienteDTO consultarCliente(@RequestParam("cpf") String cpf){
		Optional<Cliente> optionalCliente = clienteService.getByCpf(cpf);
		
		if(!optionalCliente.isPresent()) throw new RuntimeException("Cliente não cadastrado!");
		
		return convertToDTO(optionalCliente.get());
	}

	@Override
	public ICrudService<Cliente> getService() {
		return clienteService;
	}

	@Override
	public Class<Cliente> getModelClass() {
		return Cliente.class;
	}

	@Override
	public Class<ClienteDTO> getDTOClass() {
		return ClienteDTO.class;
	}
}