package org.example.tiendaspring.repositories;

import org.example.tiendaspring.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    boolean existsByNombre(String nombre);

}
