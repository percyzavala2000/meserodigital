package com.meserodigital.infrastructure.persistence.repository;

import com.meserodigital.infrastructure.persistence.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoJpaRepository extends JpaRepository<PedidoEntity, Long> {
}

