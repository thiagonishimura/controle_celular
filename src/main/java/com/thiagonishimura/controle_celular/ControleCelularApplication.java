package com.thiagonishimura.controle_celular;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thiagonishimura.controle_celular.domain.Categoria;
import com.thiagonishimura.controle_celular.repositories.CategoriaRepository;

@SpringBootApplication
public class ControleCelularApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ControleCelularApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Tela");
		Categoria cat2 = new Categoria(null, "Conector");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));		
	}
}
