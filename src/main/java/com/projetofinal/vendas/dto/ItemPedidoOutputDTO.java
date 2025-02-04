package com.projetofinal.vendas.dto;

import com.projetofinal.vendas.model.ItemPedido;
import java.math.BigDecimal;

public record ItemPedidoOutputDTO(
        ProdutoOutputDTO produto,
        Integer quantidade,
        BigDecimal precoUnitario
) {
    public ItemPedidoOutputDTO(ItemPedido item) {
        this(
                new ProdutoOutputDTO(item.getProduto()),
                item.getQuantidade(),
                item.getPrecoUnitario()
        );
    }
}
