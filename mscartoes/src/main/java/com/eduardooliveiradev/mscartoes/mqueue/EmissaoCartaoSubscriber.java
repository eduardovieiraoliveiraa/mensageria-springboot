package com.eduardooliveiradev.mscartoes.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.eduardooliveiradev.mscartoes.bean.DadoSolicitacaoCartaoBean;
import com.eduardooliveiradev.mscartoes.domain.Cartao;
import com.eduardooliveiradev.mscartoes.domain.ClienteCartao;
import com.eduardooliveiradev.mscartoes.repository.CartaoRepository;
import com.eduardooliveiradev.mscartoes.repository.ClienteCartaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class EmissaoCartaoSubscriber {

	private CartaoRepository cartaoRepository;

	private ClienteCartaoRepository clienteCartaoRepository;

	public EmissaoCartaoSubscriber(CartaoRepository cartaoRepository, ClienteCartaoRepository clienteCartaoRepository) {
		this.cartaoRepository = cartaoRepository;
		this.clienteCartaoRepository = clienteCartaoRepository;
	}

	@RabbitListener(queues = "${mq.queues.emissao-cartoes}")
	public void receberSolicitacaoEmissao(@Payload String payload) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			DadoSolicitacaoCartaoBean dadoSolicitacaoCartaoBean = mapper.readValue(payload,
					DadoSolicitacaoCartaoBean.class);

			Cartao cartao = cartaoRepository.findById(dadoSolicitacaoCartaoBean.getIdCartao()).orElseThrow();

			ClienteCartao clienteCartao = new ClienteCartao();
			clienteCartao.setCartao(cartao);
			clienteCartao.setCpf(dadoSolicitacaoCartaoBean.getCpf());
			clienteCartao.setLimite(dadoSolicitacaoCartaoBean.getLimiteLiberado());

			clienteCartaoRepository.save(clienteCartao);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}