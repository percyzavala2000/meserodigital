package com.meserodigital.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meserodigital.domain.model.Producto;
import com.meserodigital.domain.model.Producto.Estado;
import com.meserodigital.domain.repository.ProductoRepository;
import com.meserodigital.websocket.WebSocketService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private WebSocketService webSocketService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ProductoServiceImpl productoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAgregarProducto() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Pizza");

        when(productoRepository.save(producto)).thenReturn(producto);

        Producto resultado = productoService.agregarProducto(producto);

        assertEquals("Pizza", resultado.getNombre());
        verify(productoRepository, times(1)).save(producto);
        verify(webSocketService, times(1)).enviarMensaje(eq("/topic/productos"), eq(producto));
    }

    @Test
    public void testCambiarEstadoProductoExistente() throws Exception {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setEstado(Estado.DISPONIBLE);

        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));
        when(productoRepository.findAll()).thenReturn(List.of(producto));
        when(objectMapper.writeValueAsString(any())).thenReturn("[]");

        productoService.cambiarEstado(1L, Estado.NO_DISPONIBLE);

        assertEquals(Estado.NO_DISPONIBLE, producto.getEstado());
        verify(productoRepository).save(producto);
        verify(webSocketService).enviarMensaje(eq("/topic/productos"), anyString());
    }
}