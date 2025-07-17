package com.meserodigital.application.service;

import com.meserodigital.domain.model.Pedido;
import com.meserodigital.domain.model.OrdenCocina;
import com.meserodigital.domain.repository.PedidoRepository;
import com.meserodigital.domain.repository.OrdenCocinaRepository;
import com.meserodigital.domain.service.PedidoService;
import com.meserodigital.websocket.WebSocketService;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private OrdenCocinaRepository ordenCocinaRepository;

    @Autowired
    private WebSocketService webSocketService;

    @Override
    public Pedido crearPedido(Pedido pedido) {
        if (pedido.getCliente() == null || pedido.getDetalles() == null || pedido.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("El pedido debe tener cliente y al menos un detalle.");
        }

        if (pedido.getFecha() == null) {
            pedido.setFecha(LocalDateTime.now());
        }
        if (pedido.getEstado() == null) {
            pedido.setEstado(Pedido.Estado.PENDIENTE);
        }

        Pedido guardado = pedidoRepository.save(pedido);

        pedido.getDetalles().forEach(detalle -> {
            detalle.setPedido(guardado);
        });

        OrdenCocina orden = new OrdenCocina();
        orden.setIdPedido(guardado.getId());
        orden.setHoraInicio(LocalDateTime.now());
        orden.setEstado(OrdenCocina.Estado.PENDIENTE);
        ordenCocinaRepository.save(orden);

        webSocketService.enviarEstadoPedido(guardado);

        return guardado;
    }

    @Override
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public void cambiarEstado(Long id, Pedido.Estado estado) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow();
        pedido.setEstado(estado);
        pedidoRepository.save(pedido);

        webSocketService.enviarEstadoPedido(pedido);
    }

    @Override
    public void definirTiempoEntrega(Long idPedido, int minutos) {
        OrdenCocina orden = ordenCocinaRepository.findByPedidoId(idPedido)
            .stream()
            .findFirst()
            .orElseGet(() -> {
                OrdenCocina nueva = new OrdenCocina();
                nueva.setIdPedido(idPedido);
                nueva.setHoraInicio(LocalDateTime.now());
                nueva.setEstado(OrdenCocina.Estado.PREPARANDO);
                return ordenCocinaRepository.save(nueva);
            });

        orden.setTiempoEstimado(LocalTime.of(minutos / 60, minutos % 60));
        ordenCocinaRepository.save(orden);

        Pedido pedido = pedidoRepository.findById(idPedido)
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedido.setEstado(Pedido.Estado.PREPARANDO);
        pedidoRepository.save(pedido);

        webSocketService.enviarEstadoPedido(pedido);
    }

    @Override
    public void marcarComoListo(Long idPedido) {
        Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow();
        pedido.setEstado(Pedido.Estado.LISTO);
        pedidoRepository.save(pedido);

        OrdenCocina orden = ordenCocinaRepository.findByPedidoId(idPedido).stream()
            .findFirst().orElseThrow();
        orden.setEstado(OrdenCocina.Estado.LISTO);
        orden.setHoraEntrega(LocalDateTime.now());
        ordenCocinaRepository.save(orden);

        webSocketService.enviarEstadoPedido(pedido);
    }

    @Override
    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }
}
