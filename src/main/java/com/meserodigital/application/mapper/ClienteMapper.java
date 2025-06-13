package com.meserodigital.application.mapper;

import com.meserodigital.application.dto.ClienteDTO;
import com.meserodigital.domain.model.Cliente;

public class ClienteMapper {

    public static ClienteDTO toDTO(Cliente c) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(c.getId());
        dto.setNombre(c.getNombre());
        dto.setNumMesa(c.getNumMesa());
        return dto;
    }

    public static Cliente toEntity(ClienteDTO dto) {
        Cliente c = new Cliente();
        c.setId(dto.getId());
        c.setNombre(dto.getNombre());
        c.setNumMesa(dto.getNumMesa());
        return c;
    }
}
