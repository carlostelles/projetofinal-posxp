package com.projetofinal.vendas.service;

import com.projetofinal.vendas.dto.ProdutoInputDTO;
import com.projetofinal.vendas.exception.BusinessException;
import com.projetofinal.vendas.exception.ResourceNotFoundException;
import com.projetofinal.vendas.model.Produto;
import com.projetofinal.vendas.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto", id));
    }

    @Transactional
    public Produto criar(ProdutoInputDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoDTO.nome());
        produto.setDescricao(produtoDTO.descricao());
        produto.setPreco(produtoDTO.preco());
        produto.setEstoque(produtoDTO.estoque());
        produto.setCategoria(produtoDTO.categoria());
        produto.setSku(produtoDTO.sku());
        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto atualizar(Long id, ProdutoInputDTO produtoDTO) {
        Produto produtoExistente = buscarPorId(id);
        produtoExistente.setNome(produtoDTO.nome());
        produtoExistente.setDescricao(produtoDTO.descricao());
        produtoExistente.setPreco(produtoDTO.preco());
        produtoExistente.setEstoque(produtoDTO.estoque());
        produtoExistente.setCategoria(produtoDTO.categoria());
        return produtoRepository.save(produtoExistente);
    }

    @Transactional
    public void excluir(Long id) {
        Produto produto = buscarPorId(id);
        produtoRepository.delete(produto);
    }

    public List<Produto> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContaining(nome);
    }

    public Long contarProdutos() {
        return produtoRepository.count();
    }

    @Transactional
    public void reduzirEstoque(Long produtoId, Integer quantidade) {
        Produto produto = buscarPorId(produtoId);
        if (produto.getEstoque() < quantidade) {
            throw new BusinessException("Estoque insuficiente para o produto: " + produto.getNome());
        }
        produto.setEstoque(produto.getEstoque() - quantidade);
        produtoRepository.save(produto);
    }

    @Transactional
    public void aumentarEstoque(Long produtoId, Integer quantidade) {
        Produto produto = buscarPorId(produtoId);
        produto.setEstoque(produto.getEstoque() + quantidade);
        produtoRepository.save(produto);
    }
}