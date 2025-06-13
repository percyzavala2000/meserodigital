package com.meserodigital.infrastructure.persistence.adapter;

import com.meserodigital.domain.model.Cliente;
import com.meserodigital.domain.repository.ClienteRepository;
import com.meserodigital.infrastructure.persistence.entity.ClienteEntity;
import com.meserodigital.infrastructure.persistence.repository.ClienteJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClienteAdapter implements ClienteRepository {

    @Autowired
    private ClienteJpaRepository jpaRepository;

    @Override
    public Cliente save(Cliente cliente) {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(cliente.getId());
        entity.setNombre(cliente.getNombre());
        entity.setNumMesa(cliente.getNumMesa());
        entity = jpaRepository.save(entity);
        return new Cliente(entity.getId(), entity.getNombre(), entity.getNumMesa());
    }

    @Override
    public List<Cliente> findAll() {
        return jpaRepository.findAll().stream()
                .map(e -> new Cliente(e.getId(), e.getNombre(), e.getNumMesa()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cliente> findById(Long id) {
        return jpaRepository.findById(id)
                .map(e -> new Cliente(e.getId(), e.getNombre(), e.getNumMesa()));
    }
}

