package com.projetofinal.vendas.repository;

import com.projetofinal.vendas.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Métodos personalizados (ex.: buscar pedidos por status)
    // Long countByStatus(StatusPedido status);
}