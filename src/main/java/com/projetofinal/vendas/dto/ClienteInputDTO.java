package com.projetofinal.vendas.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteInputDTO(
        @NotBlank
        @Size(min = 3, max = 100)
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,

        String telefone,

        @NotBlank
        @Size(max = 200)
        String endereco
) {}