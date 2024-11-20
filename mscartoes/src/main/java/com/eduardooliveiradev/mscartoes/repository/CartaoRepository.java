package com.eduardooliveiradev.mscartoes.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduardooliveiradev.mscartoes.domain.Cartao;
import com.eduardooliveiradev.mscartoes.domain.EnumBandeiraCartao;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {

	List<Cartao> findByRendaLessThanEqual(BigDecimal renda);
	
	List<Cartao> findByNomeAndBandeira(String nome, EnumBandeiraCartao bandeira);
}
