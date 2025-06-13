package com.meserodigital.application.service;

import com.meserodigital.domain.model.Producto;
import com.meserodigital.domain.repository.ProductoRepository;
import com.meserodigital.domain.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return productoRepository.findAll();
    }

    @Override
    public void cambiarEstado(Long id, Producto.Estado estado) {
        Producto producto = productoRepository.findById(id).orElseThrow();
        producto.setEstado(estado);
        productoRepository.save(producto);
    }
}
