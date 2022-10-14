package br.com.vr.desafio.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.vr.desafio.model.Cartao;
import br.com.vr.desafio.model.dto.DadosDTO;
import br.com.vr.desafio.service.CartaoService;

@RestController
@RequestMapping(path = "/cartoes")
public class CartaoController {

	@Autowired
	private CartaoService cartaoService;

	public CartaoController() {

	}

	@GetMapping("/{numeroCartao}")
	public ResponseEntity<String> cartaoPorNumero(@PathVariable String numeroCartao) {
		ResponseEntity<String> retorno = null;
		try {
			List<Cartao> encontrado = cartaoService.obterCartaoPorNumero(numeroCartao);
			Cartao c = encontrado.get(0);

			DadosDTO saida = new DadosDTO(c.getNumeroCartao(), c.getSenha(), c.getSaldo());
			retorno = ResponseEntity.ok(saida.getSaldo().toString());
		} catch (Exception e) {
			retorno = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return retorno;
	}

	@PostMapping(produces = "application/json")
	public ResponseEntity<DadosDTO> novoCartao(@RequestBody DadosDTO dadosEntrada) {
		URI uri = null;
		Cartao novo = new Cartao(dadosEntrada.getNumeroCartao(), dadosEntrada.getSenha());
		ResponseEntity<DadosDTO> retorno = null;
		try {

			novo.setSaldo(new BigDecimal(500));
			cartaoService.salvar(novo);
			uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(novo.getId())
					.toUri();
			retorno = ResponseEntity.created(uri).body(dadosEntrada);

		} catch (Exception e) {
			retorno = new ResponseEntity<>(dadosEntrada, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		return retorno;

	}

}
