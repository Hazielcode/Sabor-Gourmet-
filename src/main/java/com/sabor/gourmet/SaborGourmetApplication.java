package com.sabor.gourmet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SaborGourmetApplication {
    public static void main(String[] args) {
        // ✅ Inicializa Spring Boot normalmente
        SpringApplication.run(SaborGourmetApplication.class, args);

        // ✅ Genera un nuevo hash usando el mismo BCrypt de tu aplicación
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Nuevo hash admin123 → " + encoder.encode("admin123"));
    }
}
