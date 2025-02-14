package org.example.tiendaspring.controllers;

import jakarta.validation.Valid;
import org.example.tiendaspring.models.Cliente;
import org.example.tiendaspring.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@CacheConfig(cacheNames = "clientes")
public class ClienteController {

    private ClienteRepository clienteRepository;

    public ClienteController() {}

    @Autowired
    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getClientes() {
        return ResponseEntity.ok(clienteRepository.findAll());
    }

    @GetMapping("/{nickname}")
    @Cacheable
    public ResponseEntity<Cliente> getCliente(@PathVariable String nickname) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Cliente cliente = clienteRepository.findByNickname(nickname).orElseThrow();
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Cliente> addCliente(@Valid @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    @PutMapping
    public ResponseEntity<Cliente> updateCliente(@Valid @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCliente(@PathVariable String id) {
        clienteRepository.deleteById(id);
        return ResponseEntity.ok("Cliente con ID " + id + " eliminado");
    }

}
