package com.ggs.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggs.cursomc.domain.Pedido;
import com.ggs.cursomc.repositories.PedidoRepository;
import com.ggs.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	public Optional<Pedido> buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		
		//if (obj == null) {
		//	throw new ObjectNotFoundException(
		//			"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName());
		//}		
		//return obj;
		return Optional.of(obj.orElseThrow(() -> new ObjectNotFoundException (
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())));
	}
}
