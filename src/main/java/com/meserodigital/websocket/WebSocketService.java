package com.meserodigital.websocket;

import com.meserodigital.domain.model.Pedido;
import com.meserodigital.domain.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void enviarMensaje(String destino, Object mensaje) {
        messagingTemplate.convertAndSend(destino, mensaje);
    }

    // Enviar todos los pedidos a un canal general
    public void enviarEstadoPedido(Pedido pedido) {
        messagingTemplate.convertAndSend("/topic/pedidos", pedido);
    }

    // Enviar estado de un pedido específico (opcional)
    public void enviarEstadoPedidoPorId(Pedido pedido) {
        messagingTemplate.convertAndSend("/topic/pedidos/" + pedido.getId(), pedido);
    }

    // Enviar productos actualizados
    public void enviarEstadoProducto(Producto producto) {
        messagingTemplate.convertAndSend("/topic/productos", producto);
    }
}
