package com.ggs.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ggs.cursomc.domain.ItemPedido;
import com.ggs.cursomc.domain.PagamentoComBoleto;
import com.ggs.cursomc.domain.Pedido;
import com.ggs.cursomc.domain.enums.EstadoPagamento;
import com.ggs.cursomc.repositories.ItemPedidoRepository;
import com.ggs.cursomc.repositories.PagamentoRepository;
import com.ggs.cursomc.repositories.PedidoRepository;
import com.ggs.cursomc.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ClienteService clienteService;

	// public Optional<Pedido> find(Integer id) {
	public Pedido find(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		// if (obj == null) {
		// throw new ObjectNotFoundException(
		// "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName());
		// }
		// return obj;
		// return Optional.of(obj.orElseThrow(() -> new ObjectNotFoundException(
		// "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName())));
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());

		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.find(ip.getProduto().getId()).get());
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);

		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;

	}
}
