package com.meserodigital.application.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meserodigital.domain.model.Producto;
import com.meserodigital.domain.repository.ProductoRepository;
import com.meserodigital.domain.service.ProductoService;
import com.meserodigital.websocket.WebSocketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProductoServiceImpl implements ProductoService {

  @Autowired
  private ProductoRepository productoRepository;

  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private WebSocketService webSocketService;
  private static final Logger logger = LoggerFactory.getLogger(ProductoServiceImpl.class);

  @Override
  public Producto agregarProducto(Producto producto) {
    Producto guardado = productoRepository.save(producto);
    webSocketService.enviarMensaje("/topic/productos", guardado);
    return guardado;
  }

  @Override
  public List<Producto> listarProductos() {
    return productoRepository.findAllWithCategoria();
  }

  
@Override
@Transactional
public void cambiarEstado(Long id, Producto.Estado estado) {
    productoRepository.findById(id).ifPresentOrElse(producto -> {
        producto.setEstado(estado);
        productoRepository.save(producto);

        List<Producto> disponibles = productoRepository.findAll().stream()
            .filter(p -> p.getEstado() == Producto.Estado.DISPONIBLE)
            .collect(Collectors.toList());

        try {
            String json = objectMapper.writeValueAsString(disponibles);

            logger.info("📤 Enviando menú actualizado por WebSocket...");
            logger.info("Mensaje JSON: {}", json);

            webSocketService.enviarMensaje("/topic/productos", json); // enviar como String JSON
        } catch (Exception e) {
            logger.error("❌ Error al convertir lista de productos a JSON", e);
        }

    }, () -> {
        throw new RuntimeException("Producto no encontrado con ID: " + id);
    });
}

  @Override
  public Producto buscarPorId(Long id) {
    return productoRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
  }
}
