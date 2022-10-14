package br.com.vr.desafio.repository;
import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.vr.desafio.model.Transacao;


@Repository
@Transactional
public interface TransacaoRepository extends CrudRepository<Transacao, Long>{
	
	
	
}
