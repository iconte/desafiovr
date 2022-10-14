package br.com.vr.desafio.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
@Entity
@Table(name = "cartao")
public class Cartao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
	@NotEmpty(message ="numero não pode ser vazio")
	
	private String numeroCartao;
	
	@Column(nullable = false)
	@DecimalMin(value = "0.0")
	private BigDecimal saldo;
	
	@Column(nullable = false)
	@NotEmpty(message = "Senha não pode ser vazio")
	private String senha;
	
	public Cartao() {
	
	}
	
	public Cartao(String numeroCartao, String senha) {
		this.saldo = new BigDecimal(500);
		this.senha = senha;
		this.numeroCartao = numeroCartao;
		
	}
	

	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(id, numeroCartao, saldo, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cartao other = (Cartao) obj;
		return Objects.equals(id, other.id) && Objects.equals(numeroCartao, other.numeroCartao)
				&& Objects.equals(saldo, other.saldo) && Objects.equals(senha, other.senha);
	}
	
	
	
	
}
