package com.meserodigital.domain.repository;

import com.meserodigital.domain.model.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoRepository {
    Producto save(Producto producto);
    Optional<Producto> findById(Long id);
    List<Producto> findAll();
    List<Producto> findByEstado(Producto.Estado estado);
}