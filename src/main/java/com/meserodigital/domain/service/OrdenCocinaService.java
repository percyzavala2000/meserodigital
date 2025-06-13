package com.meserodigital.domain.service;

import com.meserodigital.domain.model.OrdenCocina;
import java.util.List;

public interface OrdenCocinaService {
    OrdenCocina crearOrden(OrdenCocina orden);
    List<OrdenCocina> listarPorPedido(Long pedidoId);
    void cambiarEstado(Long id, OrdenCocina.Estado estado);
}
