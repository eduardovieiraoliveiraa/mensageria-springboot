package com.eduardooliveiradev.mscartoes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardooliveiradev.mscartoes.controller.dto.CartaoDTO;
import com.eduardooliveiradev.mscartoes.domain.Cartao;
import com.eduardooliveiradev.mscartoes.service.CartaoService;

import br.com.eduardo.spring.arquitetura.arquiteturaspring.controller.AbstractController;
import br.com.eduardo.spring.arquitetura.arquiteturaspring.service.ICrudService;

@RestController
@RequestMapping("/cartoes")
public class CartaoController extends AbstractController<Cartao, CartaoDTO>{

	@Autowired
    private CartaoService cartaoService;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/status")
	public String status() {
		String porta = environment.getProperty("local.server.port");
		return "ok - server port: " + porta;
	}
	
	@GetMapping("/find-cartoes-compativel-renda/{renda}")
	public List<CartaoDTO> findCartoesCompativelRenda(@PathVariable("renda") Long renda){
		List<Cartao> cartoesList = cartaoService.getCartoesRendaMenorIgual(renda);
		
		return convertToDTO(cartoesList);
	}
	
	@Override
	public ICrudService<Cartao> getService() {
		return cartaoService;
	}

	@Override
	public Class<Cartao> getModelClass() {
		return Cartao.class;
	}

	@Override
	public Class<CartaoDTO> getDTOClass() {
		return CartaoDTO.class;
	}
}