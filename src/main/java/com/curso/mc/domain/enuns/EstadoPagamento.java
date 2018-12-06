package com.curso.mc.domain.enuns;

public enum EstadoPagamento {

	PENDENTE(1, "Pagamento Pendente"), QUITADO(2, "Pagamento Quitado"), CANCELADO(3, "Pagamento Cancelado");

	private Integer cod;
	private String descricao;

	private EstadoPagamento(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static EstadoPagamento toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (EstadoPagamento ep : EstadoPagamento.values()) {
			if (cod.equals(ep.getCod())) {
				return ep;
			}
		}

		throw new IllegalArgumentException("Codigo Inválido " + cod);

	}

}
