package com.eduardooliveiradev.msclientes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	
	@GetMapping("/status")
	public String status() {
		return "ok";
	}
	
//	@PostMapping
//	public ResponseEntity<URI> save(@RequestBody ClienteDTO clienteDto) {
//		Cliente cliente = convertToEntity(clienteDto);
//		
//		clienteService.save(cliente);
//		
//		URI headerLocation = ServletUriComponentsBuilder
//							.fromCurrentRequest()
//							.query("cpf={cpf}")
//							.buildAndExpand(cliente.getCpf()).toUri();
//		
//		return ResponseEntity.created(headerLocation).build();
//	}
	
	@RequestMapping("/consultar")
	public ResponseEntity<Cliente> consultarCliente(@RequestParam("cpf") String cpf){
		Optional<Cliente> optionalCliente = clienteService.getByCpf(cpf);
		
		if(optionalCliente.isPresent()) return ResponseEntity.ok(optionalCliente.get());
	
		return ResponseEntity.notFound().build();
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