package com.meserodigital.application.service;

import com.meserodigital.domain.model.Categoria;
import com.meserodigital.domain.repository.CategoriaRepository;
import com.meserodigital.domain.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public Categoria agregarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }
    @Override
    public String getNombreCategoria(Long idCategoria) {
        Categoria categoria = categoriaRepository.findById(idCategoria).orElse(null);
        return categoria != null ? categoria.getNombre() : "SIN CATEGORÍA";
    }

    @Override
public Categoria getCategoriaById(Long idCategoria) {
    return categoriaRepository.findById(idCategoria).orElse(null);
}
}
