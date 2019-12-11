package com.thiagonishimura.controle_celular;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thiagonishimura.controle_celular.domain.Categoria;
import com.thiagonishimura.controle_celular.domain.Cidade;
import com.thiagonishimura.controle_celular.domain.Cliente;
import com.thiagonishimura.controle_celular.domain.Endereco;
import com.thiagonishimura.controle_celular.domain.Equipamento;
import com.thiagonishimura.controle_celular.domain.Estado;
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
import com.thiagonishimura.controle_celular.repositories.OrdemDeServicoRepository;
import com.thiagonishimura.controle_celular.repositories.PagamentoRepository;
import com.thiagonishimura.controle_celular.repositories.ServicoRepository;

@SpringBootApplication
public class ControleCelularApplication implements CommandLineRunner{

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
	
	public static void main(String[] args) {
		SpringApplication.run(ControleCelularApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Tela");
		Categoria cat2 = new Categoria(null, "Conector");
		
		Servico s1 = new Servico(null, "Troca de frontal", 200.00);
		Servico s2 = new Servico(null, "Troca de touch", 70.00);
		Servico s3 = new Servico(null, "Display", 80.00);

		cat1.getServicos().addAll(Arrays.asList(s1, s2, s3));
		cat2.getServicos().addAll(Arrays.asList(s2));

		s1.getCategorias().addAll(Arrays.asList(cat1));
		s2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		s3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		servicoRepository.saveAll(Arrays.asList(s1, s2, s3));
		
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
	}
}
