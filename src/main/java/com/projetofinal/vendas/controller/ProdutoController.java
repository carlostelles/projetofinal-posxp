package com.projetofinal.vendas.controller;

import com.projetofinal.vendas.dto.ProdutoInputDTO;
import com.projetofinal.vendas.dto.ProdutoOutputDTO;
import com.projetofinal.vendas.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoOutputDTO>> listarTodos() {
        return ResponseEntity.ok(
                produtoService.listarTodos().stream()
                        .map(ProdutoOutputDTO::new)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoOutputDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(new ProdutoOutputDTO(produtoService.buscarPorId(id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProdutoOutputDTO> criar(@Valid @RequestBody ProdutoInputDTO produtoDTO) {
        return ResponseEntity.ok(new ProdutoOutputDTO(produtoService.criar(produtoDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoOutputDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoInputDTO produtoDTO
    ) {
        return ResponseEntity.ok(new ProdutoOutputDTO(produtoService.atualizar(id, produtoDTO)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        produtoService.excluir(id);
    }

    @GetMapping("/search/findByName")
    public ResponseEntity<List<ProdutoOutputDTO>> buscarPorNome(@RequestParam String name) {
        return ResponseEntity.ok(
                produtoService.buscarPorNome(name).stream()
                        .map(ProdutoOutputDTO::new)
                        .toList()
        );
    }

    @GetMapping("/count")
    public ResponseEntity<Long> contarProdutos() {
        return ResponseEntity.ok(produtoService.contarProdutos());
    }
}
