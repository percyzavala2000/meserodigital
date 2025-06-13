package com.meserodigital.domain.repository;

import com.meserodigital.domain.model.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaRepository {
    Categoria save(Categoria categoria);
    Optional<Categoria> findById(Long id);
    List<Categoria> findAll();
}
