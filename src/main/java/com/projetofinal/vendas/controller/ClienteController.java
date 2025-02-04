package com.projetofinal.vendas.controller;

import com.projetofinal.vendas.dto.ClienteInputDTO;
import com.projetofinal.vendas.model.Cliente;
import com.projetofinal.vendas.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente criar(@Valid @RequestBody ClienteInputDTO clienteDTO) {
        return clienteService.criar(clienteDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente atualizar(@PathVariable Long id, @Valid @RequestBody ClienteInputDTO clienteDTO) {
        return clienteService.atualizar(id, clienteDTO);
    }

    @GetMapping("/search/findByName")
    public List<Cliente> buscarPorNome(@RequestParam String name) {
        return clienteService.buscarPorNome(name);
    }

    @GetMapping("/count")
    public Long contarClientes() {
        return clienteService.contarClientes();
    }
}