package com.thiagonishimura.controle_celular.domain.enums;

public enum EstadoServico {

	PENDENTE(1, "Pendente"),
	PRONTO(2, "Pronto"),
	CANCELADO(3, "Cancelado");

	private int cod;
	private String descricao;

	private EstadoServico(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao () {
		return descricao;
	}

	public static EstadoServico toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (EstadoServico x : EstadoServico.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}