package com.meserodigital.domain.repository;

import com.meserodigital.domain.model.OrdenCocina;
import java.util.Optional;
import java.util.List;

public interface OrdenCocinaRepository {
    OrdenCocina save(OrdenCocina orden);
    Optional<OrdenCocina> findById(Long id);
    List<OrdenCocina> findByPedidoId(Long pedidoId);
}
