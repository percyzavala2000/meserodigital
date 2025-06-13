package com.meserodigital.infrastructure.persistence.adapter;

import com.meserodigital.domain.model.Producto;
import com.meserodigital.domain.repository.ProductoRepository;
import com.meserodigital.infrastructure.persistence.entity.ProductoEntity;
import com.meserodigital.infrastructure.persistence.repository.ProductoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductoAdapter implements ProductoRepository {

    @Autowired
    private ProductoJpaRepository jpaRepository;

    @Override
    public Producto save(Producto producto) {
        ProductoEntity entity = mapToEntity(producto);
        entity = jpaRepository.save(entity);
        return mapToDomain(entity);
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return jpaRepository.findById(id).map(this::mapToDomain);
    }

    @Override
    public List<Producto> findAll() {
        return jpaRepository.findAll().stream().map(this::mapToDomain).collect(Collectors.toList());
    }

    @Override
    public List<Producto> findByEstado(Producto.Estado estado) {
        return jpaRepository.findByEstado(ProductoEntity.Estado.valueOf(estado.name()))
                .stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    // Mappers internos
    private Producto mapToDomain(ProductoEntity entity) {
        Producto p = new Producto();
        p.setId(entity.getId());
        p.setNombre(entity.getNombre());
        p.setDescripcion(entity.getDescripcion());
        p.setPrecio(entity.getPrecio());
        p.setStock(entity.getStock());
        p.setImagen(entity.getImagen());
        p.setEstado(Producto.Estado.valueOf(entity.getEstado().name()));
        // Aquí podrías mapear la categoría si la necesitas
        if (entity.getCategoria() != null) {
        com.meserodigital.domain.model.Categoria categoria = new com.meserodigital.domain.model.Categoria();
        categoria.setId(entity.getCategoria().getId());
        categoria.setNombre(entity.getCategoria().getNombre());
        p.setCategoria(categoria);
    }
        return p;
    }

    private ProductoEntity mapToEntity(Producto p) {
        ProductoEntity entity = new ProductoEntity();
        entity.setId(p.getId());
        entity.setNombre(p.getNombre());
        entity.setDescripcion(p.getDescripcion());
        entity.setPrecio(p.getPrecio());
        entity.setStock(p.getStock());
        entity.setImagen(p.getImagen());
        entity.setEstado(ProductoEntity.Estado.valueOf(p.getEstado().name()));
        // La categoría se setea aparte si corresponde
         if (p.getCategoria() != null) {
        // Asigna la categoría al entity
        com.meserodigital.infrastructure.persistence.entity.CategoriaEntity catEntity = new com.meserodigital.infrastructure.persistence.entity.CategoriaEntity();
        catEntity.setId(p.getCategoria().getId());
        entity.setCategoria(catEntity);
    }
        return entity;
    }

    @Override
public List<Producto> findAllWithCategoria() {
    return jpaRepository.findAllWithCategoria()
            .stream()
            .map(this::mapToDomain)
            .collect(Collectors.toList());
}
}
