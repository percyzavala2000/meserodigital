package com.meserodigital.domain.repository;

import com.meserodigital.domain.model.Pedido;
import java.util.Optional;
import java.util.List;

public interface PedidoRepository {
    Pedido save(Pedido pedido);
    Optional<Pedido> findById(Long id);
    List<Pedido> findAll();
}
