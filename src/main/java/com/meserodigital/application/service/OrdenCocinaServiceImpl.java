package com.meserodigital.application.service;

import com.meserodigital.domain.model.OrdenCocina;
import com.meserodigital.domain.repository.OrdenCocinaRepository;
import com.meserodigital.domain.service.OrdenCocinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenCocinaServiceImpl implements OrdenCocinaService {

    @Autowired
    private OrdenCocinaRepository ordenCocinaRepository; 

    @Override
    public OrdenCocina crearOrden(OrdenCocina orden) {
        return ordenCocinaRepository.save(orden);
    }

    @Override
    public List<OrdenCocina> listarPorPedido(Long pedidoId) {
        return ordenCocinaRepository.findByPedidoId(pedidoId);
    }

    @Override
    public void cambiarEstado(Long id, OrdenCocina.Estado estado) {
        OrdenCocina orden = ordenCocinaRepository.findById(id).orElseThrow();
        orden.setEstado(estado);
        ordenCocinaRepository.save(orden);
    }
}
