package org.example.tiendaspring.repositories;

import org.example.tiendaspring.models.Cliente;
import org.example.tiendaspring.models.Historial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistorialRepository extends JpaRepository<Historial, Integer> {

    Optional<List<Historial>> findAllByCliente(Cliente cliente_id);

}
