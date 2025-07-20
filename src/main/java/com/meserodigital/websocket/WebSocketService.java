package com.meserodigital.websocket;

import com.meserodigital.domain.model.Pedido;
import com.meserodigital.domain.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class WebSocketService {
  private static final Logger logger = LoggerFactory.getLogger(WebSocketService.class);
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    

    public void enviarMensaje(String destino, Object mensaje) {
       logger.info("📢 Enviando mensaje a {}", destino);
       logger.info("Mensaje: {}", mensaje);
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
