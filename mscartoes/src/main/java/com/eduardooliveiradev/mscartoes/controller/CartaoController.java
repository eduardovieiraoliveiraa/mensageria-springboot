package com.eduardooliveiradev.mscartoes.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eduardooliveiradev.mscartoes.controller.dto.CartaoDTO;
import com.eduardooliveiradev.mscartoes.controller.dto.ClienteCartaoDTO;
import com.eduardooliveiradev.mscartoes.domain.Cartao;
import com.eduardooliveiradev.mscartoes.domain.ClienteCartao;
import com.eduardooliveiradev.mscartoes.service.CartaoService;
import com.eduardooliveiradev.mscartoes.service.ClienteCartaoService;

import br.com.eduardo.spring.arquitetura.arquiteturaspring.controller.AbstractController;
import br.com.eduardo.spring.arquitetura.arquiteturaspring.service.ICrudService;

@RestController
@RequestMapping("/cartoes")
public class CartaoController extends AbstractController<Cartao, CartaoDTO>{

	@Autowired
    private CartaoService cartaoService;
	
	@Autowired
	private ClienteCartaoService clienteCartaoService;
	
	@Autowired
	private Environment environment;
	
	@Autowired
    private ModelMapper modelMapper;
	
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
	
	@GetMapping("/find-cartoes-por-cliente/{cpf}")
	public List<ClienteCartaoDTO> getCartoesPorCliente(@PathVariable("cpf") String cpf){
		List<ClienteCartao> clientesCartao = clienteCartaoService.findByCpf(cpf);
		return convertClienteCartaoDTO(clientesCartao);
	}
	
	private List<ClienteCartaoDTO> convertClienteCartaoDTO(List<ClienteCartao> clientesCartao) {
		List<ClienteCartaoDTO> arrayList = new ArrayList<>();
		clientesCartao.forEach(entity -> arrayList.add(convertToClienteCartaoDTO(entity)));
		return arrayList;
	}
	
	private ClienteCartaoDTO convertToClienteCartaoDTO(ClienteCartao entity) {
		return modelMapper.map(entity, ClienteCartaoDTO.class);
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