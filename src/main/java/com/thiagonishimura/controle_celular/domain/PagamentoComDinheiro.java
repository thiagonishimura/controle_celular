package com.thiagonishimura.controle_celular.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thiagonishimura.controle_celular.domain.enums.EstadoPagamento;

@Entity
public class PagamentoComDinheiro extends Pagamento {
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataVencimento;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dataPagamento;

	public PagamentoComDinheiro() {
	}

	public PagamentoComDinheiro(Integer id, EstadoPagamento estado, OrdemDeServico ordemDeServico, Date dataVencimento, Date dataPagamento) {
		super(id, estado, ordemDeServico);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}	
}