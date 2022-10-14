package br.com.vr.desafio.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.vr.desafio.model.Cartao;
import br.com.vr.desafio.model.dto.DadosDTO;
import br.com.vr.desafio.service.CartaoService;

@ExtendWith(MockitoExtension.class)
class CartaoControllerTest {

	@InjectMocks
	private CartaoController controller;

	@Mock
	private CartaoService service;

	private DadosDTO cartao;

	private List<Cartao> lista;
	private List<Cartao> lista2;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {

		this.objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

		this.cartao = new DadosDTO();
		cartao.setNumeroCartao("123456789012");
		cartao.setSenha("1234");

		this.lista = new ArrayList<>();
		this.lista2 = new ArrayList<>();
		this.lista.add(new Cartao("12344324324", "23242"));
		this.lista.add(new Cartao("12344324321", "23241"));

		this.lista2.add(new Cartao("123456789012", "1234"));
	}

	@Test
	void deveObterCartaoPorNumero() throws Exception {

		when(this.service.obterCartaoPorNumero(cartao.getNumeroCartao())).thenReturn(this.lista2);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/cartoes/123456789012")).andExpect(status().is2xxSuccessful());
	}

	@Test
	void deveRetornarErroObterCartaoPorNumeroErrado() throws Exception {

		when(this.service.obterCartaoPorNumero(cartao.getNumeroCartao())).thenReturn(this.lista2);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/cartoes/12345678901")).andExpect(status().is4xxClientError());
	}

	@Test
	void deveSalvarNovoCartao() throws JsonProcessingException, Exception {

		this.mockMvc.perform(
				post("/cartoes/").contentType("application/json").content(objectMapper.writeValueAsString(this.cartao)))
				.andExpect(status().isCreated());
	}

	@Test
	void deveRetornarErroNovoCartaoSemDados() throws JsonProcessingException, Exception {

		this.mockMvc.perform(post("/cartoes/").contentType("application/json").content(""))
				.andExpect(status().is4xxClientError());
	}
}
