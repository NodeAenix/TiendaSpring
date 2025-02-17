package org.example.tiendaspring.controllers;

import jakarta.validation.Valid;
import org.example.tiendaspring.models.Producto;
import org.example.tiendaspring.repositories.ProductoRepository;
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
@RequestMapping("/productos")
@CacheConfig(cacheNames = {"productos"})
public class ProductoController {

    private ProductoRepository productoRepository;

    public ProductoController() {}

    @Autowired
    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getProductos() {
        return ResponseEntity.ok(productoRepository.findAll());
    }

    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Producto> getProducto(@PathVariable Integer id) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Producto producto = productoRepository.findById(id).orElseThrow();
        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<?> addProducto(@Valid @RequestBody Producto producto) {
        if (productoRepository.existsByNombre(producto.getNombre())) {
            return ResponseEntity.badRequest().body("El nombre del producto ya está en uso.");
        }
        return ResponseEntity.ok(productoRepository.save(producto));
    }

    @PutMapping
    public ResponseEntity<?> updateProducto(@Valid @RequestBody Producto producto) {
        if (productoRepository.existsByNombre(producto.getNombre())) {
            return ResponseEntity.badRequest().body("El nombre del producto ya está en uso.");
        }
        return ResponseEntity.ok(productoRepository.save(producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable String id) {
        productoRepository.deleteById(Integer.valueOf(id));
        return ResponseEntity.ok("Producto con ID " + id + " borrado.");
    }

}
