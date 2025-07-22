package com.meserodigital.application.service;

import com.meserodigital.domain.model.Pedido;
import com.meserodigital.domain.model.OrdenCocina;
import com.meserodigital.domain.repository.PedidoRepository;
import com.meserodigital.domain.repository.OrdenCocinaRepository;
import com.meserodigital.websocket.WebSocketService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PedidoServiceImplTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private OrdenCocinaRepository ordenCocinaRepository;

    @Mock
    private WebSocketService webSocketService;

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDefinirTiempoEntrega() {
        Long pedidoId = 1L;
        int minutos = 45;

        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setEstado(Pedido.Estado.PENDIENTE);

        OrdenCocina orden = new OrdenCocina();
        orden.setIdPedido(pedidoId);

        when(ordenCocinaRepository.findByPedidoId(pedidoId)).thenReturn(List.of(orden));
        when(pedidoRepository.findById(pedidoId)).thenReturn(Optional.of(pedido));

        pedidoService.definirTiempoEntrega(pedidoId, minutos);

        assertNotNull(orden.getTiempoEstimado());
        assertEquals(LocalTime.of(0, 45), orden.getTiempoEstimado());
        verify(ordenCocinaRepository).save(orden);
        assertEquals(Pedido.Estado.PREPARANDO, pedido.getEstado());
        verify(pedidoRepository).save(pedido);
        verify(webSocketService).enviarEstadoPedido(pedido);
    }

    @Test
    void testMarcarComoListo() {
        Long pedidoId = 1L;

        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);
        pedido.setEstado(Pedido.Estado.PREPARANDO);

        OrdenCocina orden = new OrdenCocina();
        orden.setIdPedido(pedidoId);
        orden.setEstado(OrdenCocina.Estado.PREPARANDO);

        when(pedidoRepository.findById(pedidoId)).thenReturn(Optional.of(pedido));
        when(ordenCocinaRepository.findByPedidoId(pedidoId)).thenReturn(List.of(orden));

        pedidoService.marcarComoListo(pedidoId);

        assertEquals(Pedido.Estado.LISTO, pedido.getEstado());
        assertEquals(OrdenCocina.Estado.LISTO, orden.getEstado());
        assertNotNull(orden.getHoraEntrega());

        verify(pedidoRepository).save(pedido);
        verify(ordenCocinaRepository).save(orden);
        verify(webSocketService).enviarEstadoPedido(pedido);
    }

    @Test
    void testDefinirTiempoEntregaSinOrdenExistente() {
        Long pedidoId = 1L;
        int minutos = 20;

        Pedido pedido = new Pedido();
        pedido.setId(pedidoId);

        when(ordenCocinaRepository.findByPedidoId(pedidoId)).thenReturn(List.of());
        when(pedidoRepository.findById(pedidoId)).thenReturn(Optional.of(pedido));
        when(ordenCocinaRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        pedidoService.definirTiempoEntrega(pedidoId, minutos);

        verify(ordenCocinaRepository, times(2)).save(any());
        verify(pedidoRepository).save(pedido);
        verify(webSocketService).enviarEstadoPedido(pedido);
    }
}
