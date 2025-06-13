package com.meserodigital.application.mapper;

import com.meserodigital.application.dto.CategoriaDTO;
import com.meserodigital.domain.model.Categoria;

public class CategoriaMapper {

    public static CategoriaDTO toDTO(Categoria c) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(c.getId());
        dto.setNombre(c.getNombre());
        return dto;
    }

    public static Categoria toEntity(CategoriaDTO dto) {
        Categoria c = new Categoria();
        c.setId(dto.getId());
        c.setNombre(dto.getNombre());
        return c;
    }
}
