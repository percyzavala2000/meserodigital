package com.meserodigital.presentation.api;

import com.meserodigital.domain.model.Pedido;
import com.meserodigital.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoRestController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> listar() {
        return pedidoService.listarPedidos();
    }

    @PostMapping
    public Pedido crear(@RequestBody Pedido pedido) {
        return pedidoService.crearPedido(pedido);
    }

 

}
