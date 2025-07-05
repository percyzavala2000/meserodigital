package com.meserodigital.infrastructure.persistence.repository;

import com.meserodigital.infrastructure.persistence.entity.PedidoEntity;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface PedidoJpaRepository extends JpaRepository<PedidoEntity, Long> {
  @EntityGraph(attributePaths = {"cliente", "detalles", "detalles.producto"})
  @NonNull
    List<PedidoEntity> findAll(); 
}

