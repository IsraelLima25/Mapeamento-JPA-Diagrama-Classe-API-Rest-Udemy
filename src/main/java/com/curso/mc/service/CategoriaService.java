package com.curso.mc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.curso.mc.domain.Categoria;
import com.curso.mc.repositories.CategoriaRepository;
import com.curso.mc.service.exceptions.DataIntegrityException;
import com.curso.mc.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	/*
	 * O tipo Optional foi inserido a partir da versão do spring 2.0, tendo como
	 * objetivo evitar as exceptions do tipo "NullPointException", guardando em si
	 * um contexto da busca e não somente o valor retornado na query.
	 */
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	public List<Categoria> findAll(){
		return repo.findAll();
		
	}

	public Categoria update(Categoria obj) {
		if (obj.getId() != null) {
			return repo.save(obj);
		}

		return null;
	}

	public void delete(Integer id) {
		try {
			find(id);
			repo.deleteById(id);

		} catch (DataIntegrityViolationException ex) {

			throw new DataIntegrityException(
					"Regra de integridade violada em " + id + " Tipo " + Categoria.class.getName());
		}

	}
}
