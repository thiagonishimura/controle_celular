package com.thiagonishimura.controle_celular.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.thiagonishimura.controle_celular.domain.PagamentoComDinheiro;

@Service
public class DinheiroService {

	public void preencherPagamentoComDinheiro(PagamentoComDinheiro pagto, Date instanteDoPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 30);
		pagto.setDataVencimento(cal.getTime());
	}
}