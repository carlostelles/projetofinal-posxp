package com.projetofinal.vendas.dto;

import com.projetofinal.vendas.model.Produto;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProdutoOutputDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Integer estoque,
        String categoria,
        String sku,
        LocalDateTime dataCadastro
) {
    public ProdutoOutputDTO(Produto produto) {
        this(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getEstoque(),
                produto.getCategoria(),
                produto.getSku(),
                produto.getDataCadastro()
        );
    }
}