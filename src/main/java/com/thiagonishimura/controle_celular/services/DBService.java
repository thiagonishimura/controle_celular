package com.thiagonishimura.controle_celular.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagonishimura.controle_celular.domain.Categoria;
import com.thiagonishimura.controle_celular.domain.Cidade;
import com.thiagonishimura.controle_celular.domain.Cliente;
import com.thiagonishimura.controle_celular.domain.Endereco;
import com.thiagonishimura.controle_celular.domain.Equipamento;
import com.thiagonishimura.controle_celular.domain.Estado;
import com.thiagonishimura.controle_celular.domain.ItemOrdemDeServico;
import com.thiagonishimura.controle_celular.domain.OrdemDeServico;
import com.thiagonishimura.controle_celular.domain.Pagamento;
import com.thiagonishimura.controle_celular.domain.PagamentoComCartao;
import com.thiagonishimura.controle_celular.domain.PagamentoComDinheiro;
import com.thiagonishimura.controle_celular.domain.Servico;
import com.thiagonishimura.controle_celular.domain.enums.EstadoPagamento;
import com.thiagonishimura.controle_celular.domain.enums.EstadoServico;
import com.thiagonishimura.controle_celular.domain.enums.TipoCliente;
import com.thiagonishimura.controle_celular.repositories.CategoriaRepository;
import com.thiagonishimura.controle_celular.repositories.CidadeRepository;
import com.thiagonishimura.controle_celular.repositories.ClienteRepository;
import com.thiagonishimura.controle_celular.repositories.EnderecoRepository;
import com.thiagonishimura.controle_celular.repositories.EquipamentoRepository;
import com.thiagonishimura.controle_celular.repositories.EstadoRepository;
import com.thiagonishimura.controle_celular.repositories.ItemOrdemDeServicoRepository;
import com.thiagonishimura.controle_celular.repositories.OrdemDeServicoRepository;
import com.thiagonishimura.controle_celular.repositories.PagamentoRepository;
import com.thiagonishimura.controle_celular.repositories.ServicoRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;	
	@Autowired
	private ServicoRepository servicoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private EquipamentoRepository equipamentoRepository;
	@Autowired
	private OrdemDeServicoRepository ordemDeServicoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemOrdemDeServicoRepository ordemDePedidoRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Categoria cat1 = new Categoria(null, "Tela");
		Categoria cat2 = new Categoria(null, "Conector");
		Categoria cat3 = new Categoria(null, "Bateria");
		Categoria cat4 = new Categoria(null, "Acessórios");
		Categoria cat5 = new Categoria(null, "Flex");
		Categoria cat6 = new Categoria(null, "Software");
		Categoria cat7 = new Categoria(null, "Placa");
		
		Servico s1 = new Servico(null, "Troca de frontal", 200.00);
		Servico s2 = new Servico(null, "Troca de touch", 70.00);
		Servico s3 = new Servico(null, "Display", 80.00);
		Servico s4 = new Servico(null, "Troca de conector simples", 50.00);
		Servico s5 = new Servico(null, "Troca de bateria interna", 120.00);
		Servico s6 = new Servico(null, "Troca de bateria", 70.00);
		Servico s7 = new Servico(null, "Película de vidro", 10.00);
		Servico s8 = new Servico(null, "Troca do flex do botão power", 80.00);
		Servico s9 = new Servico(null, "Restauração de Software", 30.00);
		Servico s10 = new Servico(null, "Desbloqueio da conta Google", 80.00);
		Servico s11 = new Servico(null, "Restauração simples de placa", 100.00);

		cat1.getServicos().addAll(Arrays.asList(s1, s2, s3));
		cat2.getServicos().addAll(Arrays.asList(s4));
		cat3.getServicos().addAll(Arrays.asList(s5, s6));
		cat4.getServicos().addAll(Arrays.asList(s7));
		cat5.getServicos().addAll(Arrays.asList(s8));
		cat6.getServicos().addAll(Arrays.asList(s9, s10));
		cat7.getServicos().addAll(Arrays.asList(s11));

		s1.getCategorias().addAll(Arrays.asList(cat1));
		s2.getCategorias().addAll(Arrays.asList(cat1));
		s3.getCategorias().addAll(Arrays.asList(cat1));
		s4.getCategorias().addAll(Arrays.asList(cat2));
		s5.getCategorias().addAll(Arrays.asList(cat3));
		s6.getCategorias().addAll(Arrays.asList(cat3));
		s7.getCategorias().addAll(Arrays.asList(cat4));
		s8.getCategorias().addAll(Arrays.asList(cat5));
		s9.getCategorias().addAll(Arrays.asList(cat6));
		s10.getCategorias().addAll(Arrays.asList(cat6));
		s11.getCategorias().addAll(Arrays.asList(cat7));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		servicoRepository.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		Equipamento eq1 = new Equipamento(null, "Celular", "Samsung", "J7 Prime", 1234578, "Branco", cli1);
		Equipamento eq2 = new Equipamento(null, "Celular", "Motorola", "Moto G5S", 12344512, "Troca de tela", cli1);
		Equipamento eq3 = new Equipamento(null, "Celular", "Samsung", "J2 Prime", 1245778, "Tela trincada", cli1);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli1.getEquipamentos().addAll(Arrays.asList(eq1, eq2, eq3));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		equipamentoRepository.saveAll(Arrays.asList(eq1, eq2, eq3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		OrdemDeServico os1 = new OrdemDeServico(null, sdf.parse("30/09/2017 10:32"), EstadoServico.PENDENTE, sdf.parse("30/09/2017 10:32"), cli1, e1);
		OrdemDeServico os2 = new OrdemDeServico(null, sdf.parse("10/10/2017 19:35"), EstadoServico.PRONTO, sdf.parse("05/10/2017 15:32"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, os1, 6);
		os1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComDinheiro(null, EstadoPagamento.PENDENTE, os2, sdf.parse("20/10/2017 00:00"), null);
		os2.setPagamento(pagto2);

		cli1.getOrdemDeServico().addAll(Arrays.asList(os1, os2));

		ordemDeServicoRepository.saveAll(Arrays.asList(os1, os2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemOrdemDeServico ios1 = new ItemOrdemDeServico(os1, s1, 5.00, 1, 200.00);
		ItemOrdemDeServico ios2 = new ItemOrdemDeServico(os1, s3, 0.00, 2, 80.00);
		ItemOrdemDeServico ios3 = new ItemOrdemDeServico(os2, s2, 10.00, 1, 70.00);

		os1.getItens().addAll(Arrays.asList(ios1, ios2));
		os2.getItens().addAll(Arrays.asList(ios3));

		s1.getItens().addAll(Arrays.asList(ios1));
		s2.getItens().addAll(Arrays.asList(ios3));
		s3.getItens().addAll(Arrays.asList(ios2));

		ordemDePedidoRepository.saveAll(Arrays.asList(ios1, ios2, ios3));
		
	}

}
