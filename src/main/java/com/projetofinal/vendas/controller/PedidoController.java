package com.projetofinal.vendas.controller;

import com.projetofinal.vendas.dto.ItemPedidoInputDTO;
import com.projetofinal.vendas.dto.PedidoInputDTO;
import com.projetofinal.vendas.dto.PedidoOutputDTO;
import com.projetofinal.vendas.model.StatusPedido;
import com.projetofinal.vendas.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoOutputDTO>> listarTodos() {
        return ResponseEntity.ok(
                pedidoService.listarTodos().stream()
                        .map(PedidoOutputDTO::new)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoOutputDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(new PedidoOutputDTO(pedidoService.buscarPorId(id)));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PedidoOutputDTO> criar(@Valid @RequestBody PedidoInputDTO pedidoDTO) {
        return ResponseEntity.ok(new PedidoOutputDTO(pedidoService.criarPedido(pedidoDTO)));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PedidoOutputDTO> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusPedido status
    ) {
        return ResponseEntity.ok(new PedidoOutputDTO(pedidoService.atualizarStatus(id, status)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        pedidoService.excluir(id);
    }

    @PostMapping("/{id}/itens")
    public ResponseEntity<PedidoOutputDTO> adicionarItem(
            @PathVariable Long id,
            @Valid @RequestBody ItemPedidoInputDTO itemDTO
    ) {
        return ResponseEntity.ok(new PedidoOutputDTO(pedidoService.adicionarItem(id, itemDTO)));
    }
}