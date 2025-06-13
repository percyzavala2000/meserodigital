package com.meserodigital.domain.service;

import com.meserodigital.domain.model.Categoria;
import java.util.List;

public interface CategoriaService {
    Categoria agregarCategoria(Categoria categoria);
    List<Categoria> listarCategorias();
    String getNombreCategoria(Long idCategoria);
    Categoria getCategoriaById(Long idCategoria);
}
