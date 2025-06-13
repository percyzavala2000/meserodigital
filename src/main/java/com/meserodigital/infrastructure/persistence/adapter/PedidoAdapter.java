package com.meserodigital.infrastructure.persistence.adapter;

import com.meserodigital.domain.model.Pedido;
import com.meserodigital.domain.repository.PedidoRepository;
import com.meserodigital.infrastructure.persistence.entity.PedidoEntity;
import com.meserodigital.infrastructure.persistence.repository.PedidoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PedidoAdapter implements PedidoRepository {

    @Autowired
    private PedidoJpaRepository jpaRepository;

    @Override
    public Pedido save(Pedido pedido) {
        PedidoEntity entity = new PedidoEntity();
        entity.setId(pedido.getId());
        entity.setFecha(pedido.getFecha());
        entity.setEstado(PedidoEntity.Estado.valueOf(pedido.getEstado().name()));
        // Se debería setear el cliente y detalles si corresponde
        entity = jpaRepository.save(entity);
        // Mapea de vuelta (simplificado)
        return new Pedido(entity.getId(), entity.getFecha(), Pedido.Estado.valueOf(entity.getEstado().name()), null, null);
    }

    @Override
    public List<Pedido> findAll() {
        return jpaRepository.findAll().stream()
                .map(e -> new Pedido(e.getId(), e.getFecha(), Pedido.Estado.valueOf(e.getEstado().name()), null, null))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return jpaRepository.findById(id)
                .map(e -> new Pedido(e.getId(), e.getFecha(), Pedido.Estado.valueOf(e.getEstado().name()), null, null));
    }
}
