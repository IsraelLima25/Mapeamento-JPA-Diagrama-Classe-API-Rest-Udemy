package com.curso.mc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.mc.domain.Pedido;
import com.curso.mc.repositories.PedidoRepository;
import com.curso.mc.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	/*
	 * O tipo Optional foi inserido a partir da versão do spring 2.0, tendo como
	 * objetivo evitar as exceptions do tipo "NullPointException", guardando em si
	 * um contexto da busca e não somente o valor retornado na query.
	 */
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}
