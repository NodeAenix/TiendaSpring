package org.example.tiendaspring.repositories;

import org.example.tiendaspring.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

    Optional<Cliente> findByNickname(String nickname);

}
