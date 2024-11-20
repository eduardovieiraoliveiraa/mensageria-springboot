package com.eduardooliveiradev.msavaliadorcredito.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.eduardooliveiradev.msavaliadorcredito.controller.dto.CartaoDTO;
import com.eduardooliveiradev.msavaliadorcredito.controller.dto.ClienteCartaoDTO;

@FeignClient(name = "mscartoes", path = "/cartoes")
public interface CartaoResourceClient {

	@GetMapping("/find-cartoes-compativel-renda/{renda}")
	public List<CartaoDTO> findCartoesCompativelRenda(@PathVariable("renda") Long renda);
	
	@GetMapping("/find-cartoes-por-cliente/{cpf}")
	public List<ClienteCartaoDTO> getCartoesPorCliente(@PathVariable("cpf") String cpf);
}
