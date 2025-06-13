package com.meserodigital.domain.repository;

import com.meserodigital.domain.model.Usuario;
import java.util.Optional;

public interface UsuarioRepository {
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByNombre(String nombre);
    Usuario save(Usuario usuario);
}
