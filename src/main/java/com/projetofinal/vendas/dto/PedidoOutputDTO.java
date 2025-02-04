package com.projetofinal.vendas.dto;

import com.projetofinal.vendas.model.Pedido;
import com.projetofinal.vendas.model.StatusPedido;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoOutputDTO(
        Long id,
        LocalDateTime dataPedido,
        StatusPedido status,
        ClienteOutputDTO cliente,
        BigDecimal total,
        List<ItemPedidoOutputDTO> itens
) {
    public PedidoOutputDTO(Pedido pedido) {
        this(
                pedido.getId(),
                pedido.getDataPedido(),
                pedido.getStatus(),
                new ClienteOutputDTO(pedido.getCliente()),
                pedido.getTotal(),
                pedido.getItens().stream()
                        .map(ItemPedidoOutputDTO::new)
                        .toList()
        );
    }
}
