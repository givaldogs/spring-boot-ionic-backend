package com.ggs.cursomc.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ggs.cursomc.domain.Cliente;
import com.ggs.cursomc.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	//public ResponseEntity<?> find(@PathVariable Integer id) {
	public ResponseEntity<Optional<Cliente>> find(@PathVariable Integer id) {

		Optional<Cliente> obj = service.find(id);

		/**
		 * Cliente cat1 = new Cliente (1, "Informatica"); Cliente cat2 = new Cliente (2,
		 * "Escritorio");
		 * 
		 * List<Cliente> lista = new ArrayList<>(); lista.add(cat1); lista.add(cat2);
		 */

		return ResponseEntity.ok().body(obj);
	}

}
