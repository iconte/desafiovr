package br.com.vr.desafio.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vr.desafio.model.Cartao;
import br.com.vr.desafio.model.MensagemErroEnum;
import br.com.vr.desafio.model.Transacao;
import br.com.vr.desafio.model.dto.DadosDTO;
import br.com.vr.desafio.repository.CartaoRepository;
import br.com.vr.desafio.repository.TransacaoRepository;

@Service
public class TransacaoService {
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	
	public void efetuarTransacao(DadosDTO transacao) throws Exception{
		
		Optional.ofNullable(transacao.getValor()).orElseThrow(Exception::new);
		Optional.ofNullable(transacao.getNumeroCartao()).orElseThrow(Exception::new);
		Optional.ofNullable(transacao.getSenha()).orElseThrow(Exception::new);
		Cartao encontrado = Optional.ofNullable(cartaoRepository.findByNumeroCartao(transacao.getNumeroCartao()).stream().findFirst().orElseThrow(()->new Exception(MensagemErroEnum.CARTAO_INEXISTENTE.getDescricao()))).get();
		Transacao novo = new Transacao();
		if(encontrado.getSaldo().compareTo(transacao.getValor())<0 || transacao.getValor().equals(new BigDecimal(0))){
			throw new Exception(MensagemErroEnum.SALDO_INSUFICIENTE.getDescricao());
		}
		if(!transacao.getSenha().equals(encontrado.getSenha())) {
			throw new Exception(MensagemErroEnum.SENHA_INVALIDA.getDescricao());
		}
		encontrado.setSaldo(encontrado.getSaldo().subtract(transacao.getValor()));
		cartaoRepository.save(encontrado);
		novo.setCartao(encontrado);
		novo.setDataTransacao(new Date());
		novo.setValor(transacao.getValor());
		transacaoRepository.save(novo);
	}
	

}
