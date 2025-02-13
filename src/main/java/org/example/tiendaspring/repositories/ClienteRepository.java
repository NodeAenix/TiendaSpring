package org.example.tiendaspring.repositories;

import org.example.tiendaspring.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

    Cliente findByNickname(String nickname);

}
