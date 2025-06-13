package com.meserodigital.domain.repository;

import com.meserodigital.domain.model.DetallePedido;
import java.util.List;

public interface DetallePedidoRepository {
    DetallePedido save(DetallePedido detalle);
    List<DetallePedido> findByPedidoId(Long pedidoId);
}

