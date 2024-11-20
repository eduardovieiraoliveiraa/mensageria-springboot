package com.eduardooliveiradev.msavaliadorcredito.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.eduardooliveiradev.msavaliadorcredito.bean.CartaoAprovadoBean;
import com.eduardooliveiradev.msavaliadorcredito.bean.DadoAvaliacaoClienteBean;
import com.eduardooliveiradev.msavaliadorcredito.bean.DadoSolicitacaoCartaoBean;
import com.eduardooliveiradev.msavaliadorcredito.bean.ProtocoloSolicitacaoCartaoBean;
import com.eduardooliveiradev.msavaliadorcredito.clients.CartaoResourceClient;
import com.eduardooliveiradev.msavaliadorcredito.clients.ClienteResourceClient;
import com.eduardooliveiradev.msavaliadorcredito.controller.dto.CartaoDTO;
import com.eduardooliveiradev.msavaliadorcredito.controller.dto.ClienteCartaoDTO;
import com.eduardooliveiradev.msavaliadorcredito.controller.dto.ClienteDTO;
import com.eduardooliveiradev.msavaliadorcredito.controller.dto.RetornoAvaliacaoClienteDTO;
import com.eduardooliveiradev.msavaliadorcredito.controller.dto.SituacaoClienteDTO;
import com.eduardooliveiradev.msavaliadorcredito.mqueue.SolicitacaoEmissaoCartaoPublisher;

@Service
public class AvaliadorCreditoService {

	private ClienteResourceClient clienteResourceClient;

	private CartaoResourceClient cartaoResourceClient;

	private SolicitacaoEmissaoCartaoPublisher solicitacaoEmissaoCartaoPublisher;

	public AvaliadorCreditoService(ClienteResourceClient clienteResourceClient,
			CartaoResourceClient cartaoResourceClient,
			SolicitacaoEmissaoCartaoPublisher solicitacaoEmissaoCartaoPublisher) {
		this.clienteResourceClient = clienteResourceClient;
		this.cartaoResourceClient = cartaoResourceClient;
		this.solicitacaoEmissaoCartaoPublisher = solicitacaoEmissaoCartaoPublisher;
	}

	public SituacaoClienteDTO consultarSituacaoCliente(@PathVariable("cpf") String cpf) {
		try {
			SituacaoClienteDTO situacaoClienteDTO = new SituacaoClienteDTO();

			ClienteDTO consultarCliente = clienteResourceClient.consultarCliente(cpf);
			situacaoClienteDTO.setCliente(consultarCliente);

			List<ClienteCartaoDTO> cartoesPorCliente = cartaoResourceClient.getCartoesPorCliente(cpf);
			situacaoClienteDTO.setCartoes(cartoesPorCliente);

			return situacaoClienteDTO;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public RetornoAvaliacaoClienteDTO realizarAvaliacaoCliente(DadoAvaliacaoClienteBean dadoAvaliacaoClienteBean) {
		try {
			RetornoAvaliacaoClienteDTO retornoAvaliacaoClienteDTO = new RetornoAvaliacaoClienteDTO();
			ClienteDTO cliente = clienteResourceClient.consultarCliente(dadoAvaliacaoClienteBean.getCpf());

			List<CartaoDTO> cartoesCompativelRenda = cartaoResourceClient
					.findCartoesCompativelRenda(dadoAvaliacaoClienteBean.getRenda().longValue());

			List<CartaoAprovadoBean> cartoesAprovadosCliente = CalcularLimiteAprovado(cliente, cartoesCompativelRenda);
			retornoAvaliacaoClienteDTO.setCartoesAprovados(cartoesAprovadosCliente);

			return retornoAvaliacaoClienteDTO;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private List<CartaoAprovadoBean> CalcularLimiteAprovado(ClienteDTO cliente,
			List<CartaoDTO> cartoesCompativelRenda) {
		return cartoesCompativelRenda.stream().map(cartao -> {
			CartaoAprovadoBean cartaoAprovadoBean = new CartaoAprovadoBean();

			BigDecimal limiteBasico = cartao.getLimiteBasico();
			BigDecimal idadeCliente = BigDecimal.valueOf(cliente.getIdade());
			BigDecimal fator = idadeCliente.divide(BigDecimal.TEN);
			BigDecimal limiteAprovado = fator.multiply(limiteBasico);

			cartaoAprovadoBean.setBandeira(cartao.getBandeira());
			cartaoAprovadoBean.setCartao(cartao.getNome());
			cartaoAprovadoBean.setLimiteAprovado(limiteAprovado);

			return cartaoAprovadoBean;
		}).collect(Collectors.toList());
	}
	
	public ProtocoloSolicitacaoCartaoBean solicitarEmissaoCartao(DadoSolicitacaoCartaoBean dadoSolicitacaoCartaoBean) {
		try {
			solicitacaoEmissaoCartaoPublisher.solicitarCartoes(dadoSolicitacaoCartaoBean);
			
			return new ProtocoloSolicitacaoCartaoBean(UUID.randomUUID().toString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}