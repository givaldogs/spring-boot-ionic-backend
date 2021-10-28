package com.ggs.cursomc.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ggs.cursomc.domain.Pedido;
import com.ggs.cursomc.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Optional<Pedido> obj = service.buscar(id);
		
		/**
		Pedido cat1 = new Pedido (1, "Informatica");
		Pedido cat2 = new Pedido (2, "Escritorio");
		
		List<Pedido> lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);
		*/
		
		return ResponseEntity.ok().body(obj);
	}

}
