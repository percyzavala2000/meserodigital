package com.meserodigital.infrastructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class UsuarioEntity {

    public enum Rol {
        ADMIN
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
private String nombre;

    private String contrasena;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getNombre() {
      return nombre;
    }

    public void setNombre(String nombre) {
      this.nombre = nombre;
    }

    public String getContrasena() {
      return contrasena;
    }

    public void setContrasena(String contrasena) {
      this.contrasena = contrasena;
    }

    public Rol getRol() {
      return rol;
    }

    public void setRol(Rol rol) {
      this.rol = rol;
    }

    // Getters y setters
}
