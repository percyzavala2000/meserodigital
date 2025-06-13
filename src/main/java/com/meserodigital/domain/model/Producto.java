package com.meserodigital.domain.model;

import java.math.BigDecimal;

public class Producto {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int stock;
    private String imagen;
    private Estado estado;
    private Categoria categoria;

    public enum Estado {
        DISPONIBLE,
        NO_DISPONIBLE
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

    public String getDescripcion() {
      return descripcion;
    }

    public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
      return precio;
    }

    public void setPrecio(BigDecimal precio) {
      this.precio = precio;
    }

    public int getStock() {
      return stock;
    }

    public void setStock(int stock) {
      this.stock = stock;
    }

    public String getImagen() {
      return imagen;
    }

    public void setImagen(String imagen) {
      this.imagen = imagen;
    }

    public Estado getEstado() {
      return estado;
    }

    public void setEstado(Estado estado) {
      this.estado = estado;
    }

    public Categoria getCategoria() {
      return categoria;
    }

    public void setCategoria(Categoria categoria) {
      this.categoria = categoria;
    }

    
}
