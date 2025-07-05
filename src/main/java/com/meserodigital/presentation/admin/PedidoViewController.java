package com.meserodigital.presentation.admin;

import com.meserodigital.domain.model.Pedido;
import com.meserodigital.domain.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin/pedidos")
public class PedidoViewController {

    @Autowired
    private PedidoService pedidoService;

    // Mostrar todos los pedidos
@GetMapping
public String mostrarPedidos(Model model) {
    List<Pedido> pedidos = pedidoService.listarPedidos();
    System.out.println("Pedidos cargados: " + pedidos.size());
    pedidos.forEach(p -> System.out.println("Pedido ID: " + p.getId() + ", Cliente: " + (p.getCliente() != null ? p.getCliente().getNombre() : "SIN CLIENTE")));
    model.addAttribute("pedidos", pedidos);
    return "pedidos";
}


    // Cambiar estado a "LISTO"
    @PostMapping("/marcar-listo")
    @Transactional
    public String marcarComoListo(@RequestParam("id") Long id) {
        pedidoService.marcarComoListo(id);
        return "redirect:/admin/pedidos";
    }

    // Definir tiempo estimado
    @PostMapping("/definir-tiempo")
    @Transactional
    public String definirTiempo(@RequestParam("id") Long id, @RequestParam("minutos") int minutos) {
        pedidoService.definirTiempoEntrega(id, minutos);
        return "redirect:/admin/pedidos";
    }
}
