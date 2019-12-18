package com.thiagonishimura.controle_celular.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemOrdemDeServico implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private ItemOrdemDeServicoPK id = new ItemOrdemDeServicoPK();

	private Double desconto;
	private Integer quantidade;
	private Double preco;

	public ItemOrdemDeServico() {
	}

	public ItemOrdemDeServico(OrdemDeServico ordemDeServico, Servico servico, Double desconto, Integer quantidade, Double preco) {
		super();
		id.setOrdemDeServico(ordemDeServico);
		id.setServico(servico);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	@JsonIgnore
	public OrdemDeServico getOrdemDeServico() {
		return id.getOrdemDeServico();
	}

	public Servico getServico() {
		return id.getServico();
	}

	public ItemOrdemDeServicoPK getId() {
		return id;
	}

	public void setId(ItemOrdemDeServicoPK id) {
		this.id = id;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemOrdemDeServico other = (ItemOrdemDeServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}