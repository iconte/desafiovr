package br.com.vr.desafio.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_NULL)
public class DadosDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@JsonProperty(value = "numeroCartao")
	private String numeroCartao;
	@JsonProperty(value = "senha")
	private String senha;
	@JsonProperty(value = "saldo")
	private BigDecimal saldo;
	@JsonProperty(value = "valor")
	private BigDecimal valor;

	public DadosDTO() {
		
	}
	
	public DadosDTO(String numeroCartao, String senha, BigDecimal saldo) {
		super();
		this.numeroCartao = numeroCartao;
		this.senha = senha;
		this.saldo = saldo;
		
	}



	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	 
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "DadosDTO [numeroCartao=" + numeroCartao + ", senha=" + senha + ", saldo=" + saldo + ", valor=" + valor
				+ "]";
	}
}
