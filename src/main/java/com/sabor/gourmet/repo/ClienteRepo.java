package com.sabor.gourmet.repo;

import com.sabor.gourmet.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepo extends JpaRepository<Cliente, Long> {
}
