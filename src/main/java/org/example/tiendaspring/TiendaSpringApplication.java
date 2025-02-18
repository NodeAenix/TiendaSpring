package org.example.tiendaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TiendaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiendaSpringApplication.class, args);
    }

}
