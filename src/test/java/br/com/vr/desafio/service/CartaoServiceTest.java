package br.com.vr.desafio.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import br.com.vr.desafio.repository.CartaoRepository;

@ExtendWith(MockitoExtension.class)
class CartaoServiceTest {

	@InjectMocks
	private CartaoService service;

	@Mock
	private CartaoRepository repository;

	@Autowired
	ObjectMapper objectMapper;

	private Cartao cartao;

	private List<Cartao> lista;

	private List<Cartao>  lista2;

	@BeforeEach
	void setUp() {

		this.cartao = new Cartao();
		cartao.setNumeroCartao("123456789012");
		cartao.setSenha("1234");

		this.lista = new ArrayList<>();
		this.lista2 = new ArrayList<>();
		this.lista.add(new Cartao("12344324324", "23242"));
		this.lista.add(new Cartao("12344324321", "23241"));

		this.lista2.add(new Cartao("123456789012", "1234"));
	}

	@Test
	void deveObterCartaoPorNumero() {
		when(this.repository.findByNumeroCartao("123456789012")).thenReturn(this.lista2);
		assertThat(this.repository.findByNumeroCartao("123456789012").size()).isNotZero();
	}
	
	@Test
	void deveSalvarNovoCartao() {
		service.salvar(this.cartao);
		verify(repository).save(this.cartao);
	}

	

}
