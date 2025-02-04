package com.projetofinal.vendas.service;

import com.projetofinal.vendas.dto.ItemPedidoInputDTO;
import com.projetofinal.vendas.dto.PedidoInputDTO;
import com.projetofinal.vendas.exception.BusinessException;
import com.projetofinal.vendas.exception.ResourceNotFoundException;
import com.projetofinal.vendas.model.*;
import com.projetofinal.vendas.repository.ClienteRepository;
import com.projetofinal.vendas.repository.PedidoRepository;
import com.projetofinal.vendas.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido", id));
    }

    @Transactional
    public Pedido criarPedido(PedidoInputDTO pedidoDTO) {
        Cliente cliente = clienteRepository.findById(pedidoDTO.clienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.PENDENTE);

        for (ItemPedidoInputDTO itemDTO : pedidoDTO.itens()) {
            Produto produto = produtoRepository.findById(itemDTO.produtoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

            produto.reduzirEstoque(itemDTO.quantidade());
            produtoRepository.save(produto);

            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setQuantidade(itemDTO.quantidade());
            item.setPrecoUnitario(produto.getPreco());
            pedido.adicionarItem(item);
        }

        pedido.setTotal(pedido.calcularTotal());
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido atualizarStatus(Long id, StatusPedido novoStatus) {
        Pedido pedido = buscarPorId(id);

        // Valida se o novo status é válido
        if (!StatusPedido.isValid(novoStatus)) {
            throw new BusinessException("Status inválido: " + novoStatus);
        }

        pedido.setStatus(novoStatus);
        return pedidoRepository.save(pedido);
    }

    @Transactional
    public void excluir(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pedido", id);
        }
        pedidoRepository.deleteById(id);
    }

    @Transactional
    public Pedido adicionarItem(Long pedidoId, ItemPedidoInputDTO itemDTO) {
        Pedido pedido = buscarPorId(pedidoId);
        Produto produto = produtoRepository.findById(itemDTO.produtoId())
                .orElseThrow(() -> new ResourceNotFoundException("Produto", itemDTO.produtoId()));

        // Valida estoque
        if (produto.getEstoque() < itemDTO.quantidade()) {
            throw new BusinessException("Estoque insuficiente para o produto: " + produto.getNome());
        }

        // Cria o item do pedido
        ItemPedido item = new ItemPedido();
        item.setProduto(produto);
        item.setQuantidade(itemDTO.quantidade());
        item.setPrecoUnitario(produto.getPreco()); // Captura o preço atual
        item.setPedido(pedido);

        // Atualiza estoque e adiciona ao pedido
        produto.reduzirEstoque(itemDTO.quantidade());
        produtoRepository.save(produto);

        pedido.getItens().add(item);
        pedido.calcularTotal(); // Recalcula o total

        return pedidoRepository.save(pedido);
    }
}