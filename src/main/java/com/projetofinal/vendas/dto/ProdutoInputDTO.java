package com.projetofinal.vendas.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProdutoInputDTO(
        @NotBlank
        @Size(min = 3, max = 100)
        String nome,

        @Size(max = 500)
        String descricao,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = false)
        BigDecimal preco,

        @Min(0)
        Integer estoque,

        @NotBlank
        String categoria,

        String sku
) {}