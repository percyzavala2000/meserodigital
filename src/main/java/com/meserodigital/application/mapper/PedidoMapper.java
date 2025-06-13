package com.meserodigital.application.mapper;

import com.meserodigital.application.dto.PedidoDTO;
import com.meserodigital.application.dto.DetallePedidoDTO;
import com.meserodigital.domain.model.Pedido;

import java.util.stream.Collectors;

public class PedidoMapper {

    public static PedidoDTO toDTO(Pedido p) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(p.getId());
        dto.setFecha(p.getFecha());
        dto.setEstado(p.getEstado().name());
        dto.setIdCliente(p.getCliente() != null ? p.getCliente().getId() : null);
        dto.setDetalles(p.getDetalles() != null
                ? p.getDetalles().stream().map(DetallePedidoMapper::toDTO).collect(Collectors.toList())
                : null);
        return dto;
    }

    public static Pedido toEntity(PedidoDTO dto) {
        Pedido p = new Pedido();
        p.setId(dto.getId());
        p.setFecha(dto.getFecha());
        p.setEstado(Pedido.Estado.valueOf(dto.getEstado()));
        // Cliente y detalles se asignan aparte
        return p;
    }
}
