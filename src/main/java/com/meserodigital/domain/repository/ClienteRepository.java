package com.meserodigital.domain.repository;

import com.meserodigital.domain.model.Cliente;
import java.util.Optional;
import java.util.List;

public interface ClienteRepository {
    Cliente save(Cliente cliente);
    Optional<Cliente> findById(Long id);
    List<Cliente> findAll();
}
