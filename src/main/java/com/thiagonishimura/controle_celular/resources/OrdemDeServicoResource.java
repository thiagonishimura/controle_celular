package com.thiagonishimura.controle_celular.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thiagonishimura.controle_celular.domain.OrdemDeServico;
import com.thiagonishimura.controle_celular.services.OrdemDeServicoService;

@RestController
@RequestMapping(value="/ordensDeServico")
public class OrdemDeServicoResource {
	
	@Autowired
	private OrdemDeServicoService service;
	
	@RequestMapping (value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		OrdemDeServico obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
}
