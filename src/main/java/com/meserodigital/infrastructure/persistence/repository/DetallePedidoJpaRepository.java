package com.meserodigital.infrastructure.persistence.repository;

import com.meserodigital.infrastructure.persistence.entity.DetallePedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetallePedidoJpaRepository extends JpaRepository<DetallePedidoEntity, Long> {
    List<DetallePedidoEntity> findByPedidoId(Long pedidoId);
}

