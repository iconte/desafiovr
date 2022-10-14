package br.com.vr.desafio.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.vr.desafio.model.dto.DadosDTO;
import br.com.vr.desafio.service.TransacaoService;

@RestController
@RequestMapping(path = "/transacoes")
public class TransacaoController {

	@Autowired
	private TransacaoService transacaoService;

	@PostMapping(produces = "application/json")
	public ResponseEntity<DadosDTO> efetuarTransacao(@RequestBody DadosDTO transacao) {
		ResponseEntity retorno = null;
		URI uri = null;
		try {
			transacaoService.efetuarTransacao(transacao);
			uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/transacoes").buildAndExpand(transacao)
					.toUri();
			retorno = ResponseEntity.created(uri).body("OK");

		} catch (Exception e) {
			retorno = new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return retorno;
	}

}
