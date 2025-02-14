package org.example.tiendaspring.controllers;

import org.example.tiendaspring.services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comprar")
public class CompraController {

    private CompraService compraService;

    @Autowired
    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping
    public ResponseEntity<String> realizarCompra(@RequestParam String clienteNickname, @RequestParam Integer productoId, @RequestParam Integer cantidad) {
        String response = compraService.procesarCompra(clienteNickname, productoId, cantidad);
        return ResponseEntity.ok(response);

    }

}
