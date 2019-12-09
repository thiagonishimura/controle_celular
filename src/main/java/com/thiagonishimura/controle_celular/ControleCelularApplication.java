package com.thiagonishimura.controle_celular;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thiagonishimura.controle_celular.domain.Categoria;
import com.thiagonishimura.controle_celular.domain.Servico;
import com.thiagonishimura.controle_celular.repositories.CategoriaRepository;
import com.thiagonishimura.controle_celular.repositories.ServicoRepository;

@SpringBootApplication
public class ControleCelularApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ServicoRepository servicoRepository;
	
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
	}
}
