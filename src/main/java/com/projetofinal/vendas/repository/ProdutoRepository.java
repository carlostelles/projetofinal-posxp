package com.projetofinal.vendas.repository;

import com.projetofinal.vendas.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByNomeContaining(String nome); // Busca produtos por nome (LIKE)
    Long countBy(); // Retorna o total de produtos
}