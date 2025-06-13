package com.meserodigital.infrastructure.persistence.repository;

import com.meserodigital.infrastructure.persistence.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoJpaRepository extends JpaRepository<ProductoEntity, Long> {
    List<ProductoEntity> findByEstado(ProductoEntity.Estado estado);
}
