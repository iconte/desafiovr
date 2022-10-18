package br.com.vr.desafio.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.vr.desafio.model.Cartao;
import br.com.vr.desafio.model.Transacao;
import br.com.vr.desafio.model.dto.DadosDTO;
import br.com.vr.desafio.repository.CartaoRepository;
import br.com.vr.desafio.repository.TransacaoRepository;

@ExtendWith(MockitoExtension.class)
class TransacaoServiceTest {

	@InjectMocks
	private TransacaoService service;

	@Mock
	private TransacaoRepository repository;

	@Mock
	private CartaoRepository cartaoRepository;

	@Autowired
	ObjectMapper objectMapper;

	private DadosDTO cartao;

	private Cartao entity;

	private Transacao transacao;

	private List<Cartao> lista2;

	private List<Cartao> lista;

	private DadosDTO cartao2;

	@BeforeEach
	void setUp() {

		this.cartao = new DadosDTO();
		cartao.setNumeroCartao("123456789012");
		cartao.setSenha("1234");
		cartao.setValor(new BigDecimal(100));

		this.cartao2 = new DadosDTO();
		cartao2.setNumeroCartao("123456789012");
		cartao2.setSenha("123");
		cartao2.setValor(new BigDecimal(100));

		this.entity = new Cartao();
		this.entity.setNumeroCartao("123456789012");
		this.entity.setSenha("1234");

		this.transacao = new Transacao();
		this.transacao.setCartao(this.entity);
		this.transacao.setValor(new BigDecimal(100));

		this.lista2 = new ArrayList<>();
		this.lista2.add(new Cartao("123456789012", "1234"));
	}

	@Test()
	void deveRetornarErroRealizarTransacao() throws Exception {

		assertThrows(Exception.class, () -> service.efetuarTransacao(cartao));
	}

	@Test()
	void deveRetornarErro2RealizarTransacao() throws Exception {
		when(this.cartaoRepository.findByNumeroCartao("123456789012")).thenReturn(this.lista);
		assertThrows(Exception.class, () -> service.efetuarTransacao(cartao));
	}

	@Test()
	void deveRealizarTransacaoOk() throws Exception {
		when(this.cartaoRepository.findByNumeroCartao("123456789012")).thenReturn(this.lista);
		assertThrows(Exception.class, () -> service.efetuarTransacao(cartao2));
	}

}
