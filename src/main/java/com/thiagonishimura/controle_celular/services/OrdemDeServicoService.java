package com.thiagonishimura.controle_celular.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thiagonishimura.controle_celular.domain.ItemOrdemDeServico;
import com.thiagonishimura.controle_celular.domain.OrdemDeServico;
import com.thiagonishimura.controle_celular.domain.PagamentoComDinheiro;
import com.thiagonishimura.controle_celular.domain.enums.EstadoPagamento;
import com.thiagonishimura.controle_celular.domain.enums.EstadoServico;
import com.thiagonishimura.controle_celular.repositories.ItemOrdemDeServicoRepository;
import com.thiagonishimura.controle_celular.repositories.OrdemDeServicoRepository;
import com.thiagonishimura.controle_celular.repositories.PagamentoRepository;
import com.thiagonishimura.controle_celular.services.exceptions.ObjectNotFoundException;

@Service
public class OrdemDeServicoService {

	@Autowired
	private OrdemDeServicoRepository repo;
	
	@Autowired
	private DinheiroService dinheiroService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemOrdemDeServicoRepository itemOrdemDeServicoRepository;

	@Autowired
	private ServicoService servicoService;

	public OrdemDeServico find(Integer id) {
		Optional<OrdemDeServico> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + OrdemDeServico.class.getName()));
	}
	
	@Transactional
	public OrdemDeServico insert(OrdemDeServico obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setEstado(EstadoServico.PENDENTE);
		obj.setDataDaEntrega(null);
		
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setOrdemDeServico(obj);
		
		if (obj.getPagamento() instanceof PagamentoComDinheiro) {
			PagamentoComDinheiro pagto = (PagamentoComDinheiro) obj.getPagamento();
			dinheiroService.preencherPagamentoComDinheiro(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemOrdemDeServico ios : obj.getItens()) {
			ios.setDesconto(0.0);
			ios.setPreco(servicoService.find(ios.getServico().getId()).getPreco());
			ios.setOrdemDeServico(obj);
		}
		itemOrdemDeServicoRepository.saveAll(obj.getItens());
		return obj;
	}
}