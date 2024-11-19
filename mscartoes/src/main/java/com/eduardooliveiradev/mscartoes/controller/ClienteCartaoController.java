package com.eduardooliveiradev.mscartoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardooliveiradev.mscartoes.controller.dto.ClienteCartaoDTO;
import com.eduardooliveiradev.mscartoes.domain.ClienteCartao;
import com.eduardooliveiradev.mscartoes.service.ClienteCartaoService;

import br.com.eduardo.spring.arquitetura.arquiteturaspring.controller.AbstractController;
import br.com.eduardo.spring.arquitetura.arquiteturaspring.service.ICrudService;


@RestController
@RequestMapping("/clientecartao")
public class ClienteCartaoController extends AbstractController<ClienteCartao, ClienteCartaoDTO>{

	@Autowired
	private ClienteCartaoService clienteCartaoService;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/status")
	public String status() {
		String porta = environment.getProperty("local.server.port");
		return "ok - server port: " + porta;
	}
	
	@GetMapping("/find-cartoes-por-cliente/{cpf}")
	public List<ClienteCartaoDTO> getCartoesPorCliente(@PathVariable("cpf") String cpf){
		List<ClienteCartao> clientesCartao = clienteCartaoService.findByCpf(cpf);
		
		return convertToDTO(clientesCartao);
	}
	
	@Override
	public ICrudService<ClienteCartao> getService() {
		return clienteCartaoService;
	}

	@Override
	public Class<ClienteCartao> getModelClass() {
		return ClienteCartao.class;
	}

	@Override
	public Class<ClienteCartaoDTO> getDTOClass() {
		return ClienteCartaoDTO.class;
	}
}
