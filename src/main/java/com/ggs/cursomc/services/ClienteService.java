package com.ggs.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ggs.cursomc.domain.Cliente;
import com.ggs.cursomc.repositories.ClienteRepository;
import com.ggs.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Optional<Cliente> find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		//if (obj == null) {
		//	throw new ObjectNotFoundException(
		//			"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
		//}		
		//return obj;
		return Optional.of(obj.orElseThrow(() -> new ObjectNotFoundException (
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName())));
	}
}
