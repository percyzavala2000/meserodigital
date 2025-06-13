package com.meserodigital.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "producto")
public class ProductoEntity {

  public enum Estado {
    DISPONIBLE, NO_DISPONIBLE
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_producto") // 💡 aquí le dices el nombre real de la columna
  private Long id;

  private String nombre;

  private String descripcion;

  private BigDecimal precio;

  private int stock;

  private String imagen;

 @Enumerated(EnumType.STRING)
private Estado estado;

  @ManyToOne
  @JoinColumn(name = "id_categoria")
  private CategoriaEntity categoria;

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

  public CategoriaEntity getCategoria() {
    return categoria;
  }

  public void setCategoria(CategoriaEntity categoria) {
    this.categoria = categoria;
  }

  // Getters y setters
}
