package org.example.tiendaspring.services;

import org.example.tiendaspring.dtos.CompraDTO;
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

    public String procesarCompra(CompraDTO compraDTO) {
        Cliente cliente = clienteRepository.findByNickname(compraDTO.getClienteNickname()).orElse(null);
        if (cliente == null) {
            return "El cliente no existe";
        }

        Producto producto = productoRepository.findById(compraDTO.getProductoId()).orElse(null);
        if (producto == null) {
            return "No existe el producto";
        }
        if (producto.getStock() < compraDTO.getCantidad()) {
            return "No hay suficientes productos en stock";
        }

        Historial historial = new Historial();
        historial.setCliente(cliente);
        historial.setProducto(producto);
        historial.setFechaCompra(LocalDate.now());
        historial.setCantidad(compraDTO.getCantidad());
        historialRepository.save(historial);

        producto.setStock(producto.getStock() - compraDTO.getCantidad());
        productoRepository.save(producto);

        return "Compra realizada con éxito.";
    }

    public String procesarDevolucion(Historial historial) {
        if (historial == null) {
            return "No existe la compra";
        }

        int dias = historial.getFechaCompra().until(LocalDate.now()).getDays();
        if (dias > 30) {
            return "No se puede hacer la devolución: han pasado más de 30 días";
        }

        Producto producto = historial.getProducto();
        producto.setStock(producto.getStock() + historial.getCantidad());
        productoRepository.save(producto);

        historialRepository.delete(historial);
        return "Devolución realizada con éxito";
    }

}
