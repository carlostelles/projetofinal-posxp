package com.projetofinal.vendas.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PedidoInputDTO(
        @NotNull Long clienteId,
        @NotNull List<ItemPedidoInputDTO> itens
) {}