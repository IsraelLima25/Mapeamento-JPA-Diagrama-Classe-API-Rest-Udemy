package com.curso.mc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.mc.domain.Cliente;
import com.curso.mc.repositories.ClienteRepository;
import com.curso.mc.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;

	/*
	 * O tipo Optional foi inserido a partir da versão do spring 2.0, tendo como
	 * objetivo evitar as exceptions do tipo "NullPointException", guardando em si
	 * um contexto da busca e não somente o valor retornado na query.
	 */
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

}
