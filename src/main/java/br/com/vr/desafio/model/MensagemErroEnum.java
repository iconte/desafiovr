package br.com.vr.desafio.model;

public enum MensagemErroEnum {
	
	SALDO_INSUFICIENTE("SALDO_INSUFICIENTE"), 
	SENHA_INVALIDA("SENHA_INVALIDA"),
	CARTAO_INEXISTENTE("CARTAO_INEXISTENTE");

	private String descricao;

	private MensagemErroEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
