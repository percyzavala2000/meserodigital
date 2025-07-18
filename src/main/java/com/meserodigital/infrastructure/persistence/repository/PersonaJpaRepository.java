package com.meserodigital.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meserodigital.domain.model.Persona;

public interface PersonaJpaRepository extends JpaRepository<Persona , Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, buscar por nombre o apellido
    // List<Persona> findByNombre(String nombre);
    // List<Persona> findByApellido(String apellido);

}
