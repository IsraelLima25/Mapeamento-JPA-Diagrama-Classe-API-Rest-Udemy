package com.curso.mc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.curso.mc.domain.Pedido;
import com.curso.mc.service.PedidoService;

/*
 * Classe controladora Rest, que responde a partir do 'EndPoint' abaixo.
 */

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	/*
	 * O tipo ResponsEntity(Spring) já encapsula uma série de informações da
	 * resposta http para um serviço rest.
	 */
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Pedido obj = service.buscar(id);		
		return ResponseEntity.ok(obj);
	}
}
