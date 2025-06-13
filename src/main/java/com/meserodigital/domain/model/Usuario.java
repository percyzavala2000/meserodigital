package com.meserodigital.domain.model;

public class Usuario {
    private Long id;
    private String nombre;
    private String contrasena;  // Cifrada en la infraestructura
    private Rol rol;

    public enum Rol {
        ADMIN
    }

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
