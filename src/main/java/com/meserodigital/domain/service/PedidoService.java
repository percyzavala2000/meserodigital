package com.meserodigital.domain.service;

import com.meserodigital.domain.model.Pedido;
import java.util.List;

public interface PedidoService {
    Pedido crearPedido(Pedido pedido);
    List<Pedido> listarPedidos();
    void cambiarEstado(Long id, Pedido.Estado estado);
}
