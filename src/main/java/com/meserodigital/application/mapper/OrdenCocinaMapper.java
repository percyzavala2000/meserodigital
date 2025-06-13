package com.meserodigital.application.mapper;

import com.meserodigital.application.dto.OrdenCocinaDTO;
import com.meserodigital.domain.model.OrdenCocina;

public class OrdenCocinaMapper {

    public static OrdenCocinaDTO toDTO(OrdenCocina o) {
        OrdenCocinaDTO dto = new OrdenCocinaDTO();
        dto.setId(o.getId());
        dto.setIdPedido(o.getPedido() != null ? o.getPedido().getId() : null);
        dto.setTiempoEstimado(o.getTiempoEstimado());
        dto.setHoraInicio(o.getHoraInicio());
        dto.setHoraEntrega(o.getHoraEntrega());
        dto.setEstado(o.getEstado().name());
        return dto;
    }

    public static OrdenCocina toEntity(OrdenCocinaDTO dto) {
        OrdenCocina o = new OrdenCocina();
        o.setId(dto.getId());
        o.setTiempoEstimado(dto.getTiempoEstimado());
        o.setHoraInicio(dto.getHoraInicio());
        o.setHoraEntrega(dto.getHoraEntrega());
        o.setEstado(OrdenCocina.Estado.valueOf(dto.getEstado()));
        // Pedido se asigna aparte
        return o;
    }
}
