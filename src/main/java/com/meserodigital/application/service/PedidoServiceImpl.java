package com.meserodigital.application.service;

import com.meserodigital.domain.model.Pedido;
import com.meserodigital.domain.model.OrdenCocina;
import com.meserodigital.domain.repository.PedidoRepository;
import com.meserodigital.domain.repository.OrdenCocinaRepository;
import com.meserodigital.domain.service.PedidoService;

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

    @Override
    public Pedido crearPedido(Pedido pedido) {
        Pedido guardado = pedidoRepository.save(pedido);

        // Crear entrada en orden cocina
        OrdenCocina orden = new OrdenCocina();
        orden.setIdPedido(guardado.getId());
        orden.setHoraInicio(LocalDateTime.now());
        orden.setEstado(OrdenCocina.Estado.PREPARANDO);
        ordenCocinaRepository.save(orden);

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
    }
    
@Override
public void definirTiempoEntrega(Long idPedido, int minutos) {
    Optional<OrdenCocina> optional = ordenCocinaRepository.findByPedidoId(idPedido).stream().findFirst();

    OrdenCocina orden = optional.orElseGet(() -> {
        OrdenCocina nueva = new OrdenCocina();
        nueva.setIdPedido(idPedido);
        nueva.setHoraInicio(LocalDateTime.now());
        nueva.setEstado(OrdenCocina.Estado.PREPARANDO);
        return ordenCocinaRepository.save(nueva); // 👈 CREA ORDEN COCINA AUTOMÁTICAMENTE
    });

    orden.setTiempoEstimado(LocalTime.of(minutos / 60, minutos % 60));
    ordenCocinaRepository.save(orden);
}




    @Override
public void marcarComoListo(Long idPedido) {
    Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow();
    pedido.setEstado(Pedido.Estado.LISTO);
    pedidoRepository.save(pedido); // ← ¡Este guarda el estado actualizado!
    
    // También actualiza la orden de cocina:
    OrdenCocina orden = ordenCocinaRepository.findByPedidoId(idPedido).stream()
        .findFirst().orElseThrow();
    orden.setEstado(OrdenCocina.Estado.LISTO);
    orden.setHoraEntrega(LocalDateTime.now());
    ordenCocinaRepository.save(orden);
}
}
