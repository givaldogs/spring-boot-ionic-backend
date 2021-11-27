package com.ggs.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ggs.cursomc.domain.Categoria;
import com.ggs.cursomc.repositories.CategoriaRepository;
import com.ggs.cursomc.services.exception.DataIntegrityException;
import com.ggs.cursomc.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Optional<Categoria> find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);

		// if (obj == null) {
		// throw new ObjectNotFoundException(
		// "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName());
		// }
		// return obj;
		return Optional.of(obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName())));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Nao e possivel excluir uma Categoria que possui produtos");
		}

	}
	
	public List<Categoria> findAll() {
		return repo.findAll();
	}

}
