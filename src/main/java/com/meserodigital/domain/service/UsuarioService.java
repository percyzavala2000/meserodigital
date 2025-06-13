package com.meserodigital.domain.service;

import com.meserodigital.domain.model.Usuario;
import java.util.Optional;

public interface UsuarioService {
    Usuario registrarUsuario(Usuario usuario);
    Optional<Usuario> buscarPorNombre(String nombre);
}
