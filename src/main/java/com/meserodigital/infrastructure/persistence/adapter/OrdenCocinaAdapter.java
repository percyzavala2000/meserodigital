package com.meserodigital.infrastructure.persistence.adapter;

import com.meserodigital.domain.model.OrdenCocina;
import com.meserodigital.domain.repository.OrdenCocinaRepository;
import com.meserodigital.infrastructure.persistence.entity.OrdenCocinaEntity;
import com.meserodigital.infrastructure.persistence.repository.OrdenCocinaJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class OrdenCocinaAdapter implements OrdenCocinaRepository {

    @Autowired
    private OrdenCocinaJpaRepository jpaRepository;

    @Override
    public OrdenCocina save(OrdenCocina orden) {
        OrdenCocinaEntity entity = new OrdenCocinaEntity();
        entity.setId(orden.getId());
        entity.setTiempoEstimado(orden.getTiempoEstimado());
        entity.setHoraInicio(orden.getHoraInicio());
        entity.setHoraEntrega(orden.getHoraEntrega());
        entity.setEstado(OrdenCocinaEntity.Estado.valueOf(orden.getEstado().name()));
        // Aquí deberías setear el pedido si es necesario (cargar el PedidoEntity)
        entity = jpaRepository.save(entity);
        return mapToDomain(entity);
    }

    @Override
    public Optional<OrdenCocina> findById(Long id) {
        return jpaRepository.findById(id)
                .map(this::mapToDomain);
    }

    @Override
    public List<OrdenCocina> findByPedidoId(Long pedidoId) {
        return jpaRepository.findByPedidoId(pedidoId).stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    private OrdenCocina mapToDomain(OrdenCocinaEntity e) {
        OrdenCocina o = new OrdenCocina();
        o.setId(e.getId());
        o.setIdPedido(e.getPedido() != null ? e.getPedido().getId() : null);
        o.setTiempoEstimado(e.getTiempoEstimado());
        o.setHoraInicio(e.getHoraInicio());
        o.setHoraEntrega(e.getHoraEntrega());
        o.setEstado(OrdenCocina.Estado.valueOf(e.getEstado().name()));
        return o;
    }
}
