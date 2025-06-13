package com.meserodigital.application.service;

import com.meserodigital.domain.model.Producto;
import com.meserodigital.domain.repository.ProductoRepository;
import com.meserodigital.domain.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto agregarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAllWithCategoria();
    }
    

   @Override
@Transactional
public void cambiarEstado(Long id, Producto.Estado estado) {
    productoRepository.findById(id).ifPresentOrElse(producto -> {
        producto.setEstado(estado);  // Cambia el estado en el modelo
        productoRepository.save(producto);  // Guarda el cambio en la base de datos
    }, () -> {
        throw new RuntimeException("Producto no encontrado con ID: " + id);
    });
}
}
