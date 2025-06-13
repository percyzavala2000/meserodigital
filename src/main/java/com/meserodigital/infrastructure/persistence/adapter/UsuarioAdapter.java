package com.meserodigital.infrastructure.persistence.adapter;

import com.meserodigital.domain.model.Usuario;
import com.meserodigital.domain.repository.UsuarioRepository;
import com.meserodigital.infrastructure.persistence.entity.UsuarioEntity;
import com.meserodigital.infrastructure.persistence.repository.UsuarioJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UsuarioAdapter implements UsuarioRepository {

    @Autowired
    private UsuarioJpaRepository jpaRepository;

    @Override
    public Optional<Usuario> findById(Long id) {
        return jpaRepository.findById(id)
            .map(this::mapToDomain);
    }

    @Override
    public Optional<Usuario> findByNombre(String nombre) {
        return jpaRepository.findByNombre(nombre)
            .map(this::mapToDomain);
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(usuario.getId());
        entity.setNombre(usuario.getNombre());
        entity.setRol(UsuarioEntity.Rol.valueOf(usuario.getRol().name()));


        UsuarioEntity saved = jpaRepository.save(entity);
        return mapToDomain(saved);
    }

    // Método auxiliar de mapeo
    private Usuario mapToDomain(UsuarioEntity e) {
        Usuario u = new Usuario();
        u.setId(e.getId());
        u.setNombre(e.getNombre());
        u.setRol(Usuario.Rol.valueOf(e.getRol().name()));
        return u;
    }
}
