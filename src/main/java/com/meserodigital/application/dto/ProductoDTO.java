package com.meserodigital.application.dto;

import java.math.BigDecimal;

public class ProductoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int stock;
    private String imagen;
    private String estado;
    private Long idCategoria;
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
    public String getEstado() {
      return estado;
    }
    public void setEstado(String estado) {
      this.estado = estado;
    }
    public Long getIdCategoria() {
      return idCategoria;
    }
    public void setIdCategoria(Long idCategoria) {
      this.idCategoria = idCategoria;
    }

    
}