package com.projetofinal.vendas.model;

import com.projetofinal.vendas.exception.BusinessException;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "produtos")
@EntityListeners(AuditingEntityListener.class)
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String nome;

    @Size(max = 500)
    private String descricao;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal preco;

    @Min(0)
    private Integer estoque;

    @NotBlank
    private String categoria;

    @Column(unique = true)
    private String sku;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime dataCadastro;

    public void reduzirEstoque(Integer quantidade) {
        if (estoque >= quantidade) {
            estoque -= quantidade;
        } else {
            throw new BusinessException("Estoque insuficiente para o produto: " + nome);
        }
    }

    public void aumentarEstoque(Integer quantidade) {
        if (quantidade > 0) {
            this.estoque += quantidade;
        }
    }
}