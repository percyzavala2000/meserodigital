package com.meserodigital.infrastructure.persistence.adapter;

import com.meserodigital.domain.model.Categoria;
import com.meserodigital.domain.repository.CategoriaRepository;
import com.meserodigital.infrastructure.persistence.entity.CategoriaEntity;
import com.meserodigital.infrastructure.persistence.repository.CategoriaJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CategoriaAdapter implements CategoriaRepository {

    @Autowired
    private CategoriaJpaRepository jpaRepository;

    @Override
    public Categoria save(Categoria categoria) {
        CategoriaEntity entity = new CategoriaEntity();
        entity.setId(categoria.getId());
        entity.setNombre(categoria.getNombre());
        entity = jpaRepository.save(entity);
        return new Categoria(entity.getId(), entity.getNombre());
    }

    @Override
    public List<Categoria> findAll() {
        return jpaRepository.findAll().stream()
                .map(e -> new Categoria(e.getId(), e.getNombre()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Categoria> findById(Long id) {
        return jpaRepository.findById(id)
                .map(e -> new Categoria(e.getId(), e.getNombre()));
    }
}

