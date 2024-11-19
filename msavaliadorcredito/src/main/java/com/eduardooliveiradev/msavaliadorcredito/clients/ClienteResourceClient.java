package com.eduardooliveiradev.msavaliadorcredito.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.eduardooliveiradev.msavaliadorcredito.controller.dto.ClienteDTO;

@FeignClient(value = "msclientes", path = "/clientes")
public interface ClienteResourceClient {

	@GetMapping("/consultar")
	ClienteDTO consultarCliente(@RequestParam("cpf") String cpf);
}
