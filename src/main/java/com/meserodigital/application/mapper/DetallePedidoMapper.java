package com.meserodigital.application.mapper;

import com.meserodigital.application.dto.DetallePedidoDTO;
import com.meserodigital.domain.model.DetallePedido;

public class DetallePedidoMapper {

    public static DetallePedidoDTO toDTO(DetallePedido d) {
        DetallePedidoDTO dto = new DetallePedidoDTO();
        dto.setId(d.getId());
        dto.setIdProducto(d.getProducto() != null ? d.getProducto().getId() : null);
        dto.setCantidad(d.getCantidad());
        dto.setPrecioUnitario(d.getPrecioUnitario());
        return dto;
    }

    public static DetallePedido toEntity(DetallePedidoDTO dto) {
        DetallePedido d = new DetallePedido();
        d.setId(dto.getId());
        d.setCantidad(dto.getCantidad());
        d.setPrecioUnitario(dto.getPrecioUnitario());
        // El producto se asigna aparte
        return d;
    }
}
