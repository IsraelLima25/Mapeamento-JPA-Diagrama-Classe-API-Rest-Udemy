package com.curso.mc.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.curso.mc.domain.ItemPedido;
import com.curso.mc.domain.PagamentoComBoleto;
import com.curso.mc.domain.Pedido;
import com.curso.mc.domain.enuns.EstadoPagamento;
import com.curso.mc.repositories.ItemPedidoRepository;
import com.curso.mc.repositories.PagamentoRepository;
import com.curso.mc.repositories.PedidoRepository;
import com.curso.mc.repositories.ProdutoRepository;
import com.curso.mc.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagtoRepository;

	@Autowired
	private ProdutoRepository prodRepository;

	/*
	 * O tipo Optional foi inserido a partir da versão do spring 2.0, tendo como
	 * objetivo evitar as exceptions do tipo "NullPointException", guardando em si
	 * um contexto da busca e não somente o valor retornado na query.
	 */

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}

		obj = repo.save(obj);
		pagtoRepository.save(obj.getPagamento());

		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.00);
			ip.setPreco(prodRepository.findById(ip.getProduto().getId()).get().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());

		return obj;

	}
}
