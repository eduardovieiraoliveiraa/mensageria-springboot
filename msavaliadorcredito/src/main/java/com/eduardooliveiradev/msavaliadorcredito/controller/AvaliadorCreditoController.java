package com.eduardooliveiradev.msavaliadorcredito.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardooliveiradev.msavaliadorcredito.controller.dto.SituacaoClienteDTO;
import com.eduardooliveiradev.msavaliadorcredito.service.AvaliadorCreditoService;

@RestController
@RequestMapping("/avaliacoescredito")
public class AvaliadorCreditoController {

	@Autowired
	private AvaliadorCreditoService avaliadorCreditoService;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/status")
	public String status() {
		String porta = environment.getProperty("local.server.port");
		
		return "ok - server port: " + porta;
	}
	
	@GetMapping("/situacao-cliente/{cpf}")
	public SituacaoClienteDTO consultarSituacaoCliente(@PathVariable("cpf") String cpf) {
		return avaliadorCreditoService.consultarSituacaoCliente(cpf);
	}
}