package com.projetofinal.vendas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    // Construtor com mensagem personalizada
    public ResourceNotFoundException(String message) {
        super(message);
    }

    // Construtor para gerar mensagem automática com base no nome do recurso e ID
    public ResourceNotFoundException(String resourceName, Long id) {
        super(String.format("%s não encontrado(a) com o ID: %s", resourceName, id));
    }
}