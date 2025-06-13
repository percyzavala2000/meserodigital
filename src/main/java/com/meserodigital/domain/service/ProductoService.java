package com.meserodigital.domain.service;

import com.meserodigital.domain.model.Producto;
import java.util.List;

public interface ProductoService {
    Producto agregarProducto(Producto producto);
    List<Producto> listarProductos();
    void cambiarEstado(Long id, Producto.Estado estado);
}
