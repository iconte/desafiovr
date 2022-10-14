package br.com.vr.desafio.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.vr.desafio.model.dto.DadosDTO;
import br.com.vr.desafio.repository.CartaoRepository;

@SpringBootTest
@AutoConfigureMockMvc
class TransacaoControllerTest {
	
	@MockBean
	private CartaoRepository repo;
	
	
	private DadosDTO cartao;
	
	
	
	 @Autowired
	    private ObjectMapper objectMapper;
	 
	 @Autowired
	    private MockMvc mockMvc;
	
	 @BeforeEach
	void setUp() {
		 this.cartao = new DadosDTO();
			cartao.setNumeroCartao("123456789012");
			cartao.setSenha("1234");
			cartao.setValor(new BigDecimal(100));
    }
	
	@Test
	 void testTransacaoVazia() throws JsonProcessingException, Exception {
		
		this.mockMvc.perform(post("/transacoes")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(this.cartao)))
		.andExpect(status().is4xxClientError());
	}
	

	

}
