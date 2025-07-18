package com.meserodigital.infrastructure.persistence.repository;

import com.meserodigital.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UsuarioJpaRepository extends JpaRepository<Usuario, Long> {
   Usuario findByUsername(String username); // Método para buscar un usuario por su nombre de usuario
    
}
