package org.example.tiendaspring.controllers;

import jakarta.validation.Valid;
import org.example.tiendaspring.models.Historial;
import org.example.tiendaspring.repositories.HistorialRepository;
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
@RequestMapping("/historial")
@CacheConfig(cacheNames = {"historial"})
public class HistorialController {

    private HistorialRepository historialRepository;

    public HistorialController() {}

    @Autowired
    public HistorialController(HistorialRepository historialRepository) {
        this.historialRepository = historialRepository;
    }

    @GetMapping
    public ResponseEntity<List<Historial>> getProductos() {
        return ResponseEntity.ok(historialRepository.findAll());
    }

    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Historial> getProducto(@PathVariable Integer id) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Historial historial = historialRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(historial);
    }

    @PostMapping
    public ResponseEntity<Historial> addProducto(@Valid @RequestBody Historial historial) {
        return ResponseEntity.ok(historialRepository.save(historial));
    }

    @PutMapping
    public ResponseEntity<Historial> updateProducto(@Valid @RequestBody Historial historial) {
        return ResponseEntity.ok(historialRepository.save(historial));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable String id) {
        historialRepository.deleteById(Integer.valueOf(id));
        return ResponseEntity.ok("Historial con ID " + id + " borrado.");
    }

}
