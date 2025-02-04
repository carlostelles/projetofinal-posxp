package com.projetofinal.vendas.model;

import com.projetofinal.vendas.exception.BusinessException;

import java.util.Arrays;

public enum StatusPedido {
    PENDENTE,
    PROCESSANDO,
    ENVIADO,
    ENTREGUE,
    CANCELADO;

    /**
     * Verifica se um status é válido (existe na lista de valores do enum).
     *
     * @param status Status a ser validado.
     * @return true se o status for válido, false caso contrário.
     */
    public static boolean isValid(StatusPedido status) {
        if (status == null) {
            return false;
        }

        return Arrays.asList(StatusPedido.values()).contains(status);
    }

    /**
     * Método auxiliar para converter uma String em StatusPedido (opcional).
     *
     * @param status String representando o status.
     * @return StatusPedido correspondente.
     * @throws BusinessException Se o status não for válido.
     */
    public static StatusPedido fromString(String status) {
        try {
            return StatusPedido.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BusinessException("Status inválido: " + status);
        }
    }
}
