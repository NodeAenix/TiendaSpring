package org.example.tiendaspring.services;

import org.example.tiendaspring.models.Cliente;
import org.example.tiendaspring.models.Historial;
import org.example.tiendaspring.models.Producto;
import org.example.tiendaspring.repositories.ClienteRepository;
import org.example.tiendaspring.repositories.HistorialRepository;
import org.example.tiendaspring.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CompraService {

    private final ProductoRepository productoRepository;
    private final ClienteRepository clienteRepository;
    private final HistorialRepository historialRepository;

    @Autowired
    public CompraService(ProductoRepository productoRepository, ClienteRepository clienteRepository, HistorialRepository historialRepository) {
        this.productoRepository = productoRepository;
        this.clienteRepository = clienteRepository;
        this.historialRepository = historialRepository;
    }

    public String procesarCompra(String clienteNickname, Integer productoId, Integer cantidad) {
        Cliente cliente = clienteRepository.findByNickname(clienteNickname).orElse(null);
        if (cliente == null) {
            return "El cliente no existe";
        }

        Producto producto = productoRepository.findById(productoId).orElse(null);
        if (producto == null) {
            return "No existe el producto";
        }
        if (producto.getStock() < cantidad) {
            return "No hay suficientes productos en stock";
        }

        Historial historial = new Historial();
        historial.setCliente(cliente);
        historial.setProducto(producto);
        historial.setFechaCompra(LocalDate.now());
        historial.setCantidad(cantidad);
        historialRepository.save(historial);

        producto.setStock(producto.getStock() - cantidad);
        productoRepository.save(producto);

        return "Compra realizada con éxito.";
    }

}
