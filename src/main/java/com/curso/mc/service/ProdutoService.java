package com.curso.mc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.curso.mc.domain.Categoria;
import com.curso.mc.domain.Produto;
import com.curso.mc.repositories.CategoriaRepository;
import com.curso.mc.repositories.ProdutoRepository;
import com.curso.mc.service.exceptions.ObjectNotFoundException;


@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository prodRepo;
	@Autowired
	private CategoriaRepository catRepo;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = prodRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}


	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		@SuppressWarnings("deprecation")
		PageRequest pageRequest = new PageRequest(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = catRepo.findAllById(ids);		
		return prodRepo.search(nome, categorias, pageRequest);
	}

}
