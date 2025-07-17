package com.meserodigital.presentation.admin;

import com.meserodigital.domain.model.Producto;
import com.meserodigital.domain.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ProductoWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ProductoService productoService;

    public void notificarCambioProducto(Producto productoActualizado) {
        messagingTemplate.convertAndSend("/topic/productos", productoActualizado);
    }
}
