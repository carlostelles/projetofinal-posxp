package com.projetofinal.vendas.dto;

import com.projetofinal.vendas.model.Cliente;
import java.time.LocalDateTime;

public record ClienteOutputDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        String telefone,
        String endereco,
        LocalDateTime dataCadastro
) {
    public ClienteOutputDTO(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getEndereco(),
                cliente.getDataCadastro()
        );
    }
}
