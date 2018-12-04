package com.curso.mc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.mc.domain.Categoria;
import com.curso.mc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	/*
	 * O tipo Optional foi inserido a partir da versão do spring 2.0, tendo como objetivo evitar as exceptions
	 * do tipo "NullPointException", guardando em si um contexto da busca e não somente o valor retornado na query.
	 */
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}
}


