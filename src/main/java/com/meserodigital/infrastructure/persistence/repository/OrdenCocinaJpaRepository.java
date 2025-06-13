package com.meserodigital.infrastructure.persistence.repository;

import com.meserodigital.infrastructure.persistence.entity.OrdenCocinaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdenCocinaJpaRepository extends JpaRepository<OrdenCocinaEntity, Long> {
    List<OrdenCocinaEntity> findByPedidoId(Long pedidoId);
}
