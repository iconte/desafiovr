package br.com.vr.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vr.desafio.model.Cartao;
import br.com.vr.desafio.repository.CartaoRepository;

@Service
public class CartaoService {
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	
	public List<Cartao> listarCartoes(){
		return (List<Cartao>) cartaoRepository.findAll();
	}
	
	public List<Cartao> obterCartaoPorNumero(String numero){
		return  cartaoRepository.findByNumeroCartao(numero);
	}
	
	public void salvar(Cartao cartao){
		cartaoRepository.save(cartao);
	}
	

}
