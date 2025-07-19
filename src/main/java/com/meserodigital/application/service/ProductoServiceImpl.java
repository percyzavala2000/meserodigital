package com.meserodigital.application.service;

import com.meserodigital.domain.model.Producto;
import com.meserodigital.domain.repository.ProductoRepository;
import com.meserodigital.domain.service.ProductoService;
import com.meserodigital.websocket.WebSocketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private WebSocketService webSocketService;

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
            Producto actualizado = productoRepository.save(producto);
            System.out.println("Producto actualizado. entra aqui chcochera: " + actualizado.getNombre() + " a " + actualizado.getEstado());

            webSocketService.enviarMensaje("/topic/productos", actualizado);
            
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
