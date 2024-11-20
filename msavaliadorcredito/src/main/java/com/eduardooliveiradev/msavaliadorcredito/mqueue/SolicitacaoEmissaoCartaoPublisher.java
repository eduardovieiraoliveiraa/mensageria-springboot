package com.eduardooliveiradev.msavaliadorcredito.mqueue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.eduardooliveiradev.msavaliadorcredito.bean.DadoSolicitacaoCartaoBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SolicitacaoEmissaoCartaoPublisher {

	private RabbitTemplate rabbitTemplate;

	private Queue queueEmissaoCartoes;
	
	public SolicitacaoEmissaoCartaoPublisher(RabbitTemplate rabbitTemplate, Queue queueEmissaoCartoes) {
		this.rabbitTemplate = rabbitTemplate;
		this.queueEmissaoCartoes = queueEmissaoCartoes;
	}

	public void solicitarCartoes(DadoSolicitacaoCartaoBean dadoSolicitacaoCartaoBean) throws JsonProcessingException {
		String dadoSolicitacaoCartaoBeanJson = convertIntoJson(dadoSolicitacaoCartaoBean);
		
		rabbitTemplate.convertAndSend(queueEmissaoCartoes.getName(),dadoSolicitacaoCartaoBeanJson);
	}
	
	private String convertIntoJson(DadoSolicitacaoCartaoBean dadoSolicitacaoCartaoBean) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.writeValueAsString(dadoSolicitacaoCartaoBean);
	}
}