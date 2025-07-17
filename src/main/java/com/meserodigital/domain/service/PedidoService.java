package com.meserodigital.domain.service;

import com.meserodigital.domain.model.Pedido;
import java.util.List;

public interface PedidoService {
   Pedido buscarPorId(Long id);
    Pedido crearPedido(Pedido pedido);
    List<Pedido> listarPedidos();
    void cambiarEstado(Long id, Pedido.Estado estado);
    void definirTiempoEntrega(Long idPedido, int minutos);
    void marcarComoListo(Long idPedido);

}
