package com.meserodigital.application.mapper;

import com.meserodigital.application.dto.ProductoDTO;
import com.meserodigital.domain.model.Producto;

public class ProductoMapper {

    public static ProductoDTO toDTO(Producto p) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(p.getId());
        dto.setNombre(p.getNombre());
        dto.setDescripcion(p.getDescripcion());
        dto.setPrecio(p.getPrecio());
        dto.setStock(p.getStock());
        dto.setImagen(p.getImagen());
        dto.setEstado(p.getEstado().name());
        dto.setIdCategoria(p.getCategoria() != null ? p.getCategoria().getId() : null);
        return dto;
    }

    public static Producto toEntity(ProductoDTO dto) {
        Producto p = new Producto();
        p.setId(dto.getId());
        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setPrecio(dto.getPrecio());
        p.setStock(dto.getStock());
        p.setImagen(dto.getImagen());
        p.setEstado(Producto.Estado.valueOf(dto.getEstado()));
        // La categoría debería mapearse aparte
        return p;
    }
}
