package com.projetofinal.vendas.service;

import com.projetofinal.vendas.dto.ClienteInputDTO;
import com.projetofinal.vendas.exception.ResourceNotFoundException;
import com.projetofinal.vendas.model.Cliente;
import com.projetofinal.vendas.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente", id));
    }

    @Transactional
    public Cliente criar(ClienteInputDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.nome());
        cliente.setEmail(clienteDTO.email());
        cliente.setCpf(clienteDTO.cpf());
        cliente.setTelefone(clienteDTO.telefone());
        cliente.setEndereco(clienteDTO.endereco());
        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente atualizar(Long id, ClienteInputDTO clienteDTO) {
        Cliente clienteExistente = buscarPorId(id);
        clienteExistente.setNome(clienteDTO.nome());
        clienteExistente.setEmail(clienteDTO.email());
        clienteExistente.setTelefone(clienteDTO.telefone());
        clienteExistente.setEndereco(clienteDTO.endereco());
        return clienteRepository.save(clienteExistente);
    }

    @Transactional
    public void excluir(Long id) {
        Cliente cliente = buscarPorId(id);
        clienteRepository.delete(cliente);
    }

    public List<Cliente> buscarPorNome(String nome) {
        return clienteRepository.findByNomeContaining(nome);
    }

    public Long contarClientes() {
        return clienteRepository.count();
    }
}
