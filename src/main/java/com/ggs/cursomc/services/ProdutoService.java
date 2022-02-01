package com.ggs.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ggs.cursomc.domain.Categoria;
import com.ggs.cursomc.domain.Produto;
import com.ggs.cursomc.repositories.CategoriaRepository;
import com.ggs.cursomc.repositories.ProdutoRepository;
import com.ggs.cursomc.services.exception.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Optional<Produto> find(Integer id) {
		Optional<Produto> obj = repo.findById(id);
		
		//if (obj == null) {
		//	throw new ObjectNotFoundException(
		//			"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName());
		//}		
		//return obj;
		return Optional.of(obj.orElseThrow(() -> new ObjectNotFoundException (
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName())));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.search(nome, categorias, pageRequest);
		
	}

}
