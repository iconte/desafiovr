package br.com.vr.desafio.repository;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.vr.desafio.model.Cartao;


@Repository
@Transactional
public interface CartaoRepository extends CrudRepository<Cartao, Long>{
	
	public List<Cartao> findByNumeroCartao(String numeroCartao);
	
}
