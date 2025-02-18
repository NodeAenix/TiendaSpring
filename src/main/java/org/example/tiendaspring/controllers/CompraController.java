package org.example.tiendaspring.controllers;

import org.example.tiendaspring.dtos.CompraDTO;
import org.example.tiendaspring.models.Historial;
import org.example.tiendaspring.repositories.HistorialRepository;
import org.example.tiendaspring.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/compras")
@CacheConfig(cacheNames = "compras")
public class CompraController {

    private CompraService compraService;
    private HistorialRepository historialRepository;

    public CompraController() {}

    @Autowired
    public CompraController(CompraService compraService, HistorialRepository historialRepository) {
        this.compraService = compraService;
        this.historialRepository = historialRepository;
    }

    @GetMapping
    public ResponseEntity<List<Historial>> getCompras() {
        return ResponseEntity.ok(historialRepository.findAll());
    }

    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Historial> getCompra(@PathVariable Integer id) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Historial historial = historialRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(historial);
    }

    @GetMapping("/devolver/{id}")
    public ResponseEntity<String> realizarDevolucion(@PathVariable Integer id) {
        Historial historial = historialRepository.findById(id).orElse(null);
        return ResponseEntity.ok(compraService.procesarDevolucion(historial));
    }

    @PostMapping
    public ResponseEntity<String> addCompra(@RequestBody CompraDTO compraDTO) {
        String response = compraService.procesarCompra(compraDTO);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<String> updateCompra(@RequestBody CompraDTO compraDTO) {
        String response = compraService.procesarCompra(compraDTO);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompra(@PathVariable String id) {
        historialRepository.deleteById(Integer.valueOf(id));
        return ResponseEntity.ok("Compra con ID " + id + " borrado.");
    }

}
