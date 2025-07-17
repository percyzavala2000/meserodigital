package com.meserodigital.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {
  private Long id;
  private LocalDateTime fecha;
  private Estado estado;
  private Cliente cliente;
  private List<DetallePedido> detalles;
  private OrdenCocina ordenCocina;
  private int tiempoEntrega;

  
  
  public Pedido(Long id, LocalDateTime fecha, Estado estado, Cliente cliente, List<DetallePedido> detalles , int tiempoEntrega) {
    this.id = id;
    this.fecha = fecha;
    this.estado = estado;
    this.cliente = cliente;
    this.detalles = detalles;
    this.tiempoEntrega = tiempoEntrega;
  }
  
  public Pedido() {
  }
  
  public enum Estado {
    PENDIENTE,
    PREPARANDO,
    LISTO,
    RECOGIDO
  }
  
  public Long getId() {
    return id;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  
  
  public LocalDateTime getFecha() {
    return fecha;
  }
  
  public void setFecha(LocalDateTime fecha) {
    this.fecha = fecha;
  }
  
  public Estado getEstado() {
    return estado;
  }
  
  public void setEstado(Estado estado) {
    this.estado = estado;
  }
  
  public Cliente getCliente() {
    return cliente;
  }
  
  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }
  
  public List<DetallePedido> getDetalles() {
    return detalles;
  }
  
  public void setDetalles(List<DetallePedido> detalles) {
    this.detalles = detalles;
  }
  
  public OrdenCocina getOrdenCocina() {
    return ordenCocina;
  }
  
  public void setOrdenCocina(OrdenCocina ordenCocina) {
    this.ordenCocina = ordenCocina;
  }
  
  public int getTiempoEntrega() {
    return tiempoEntrega;
  }
  
  public void setTiempoEntrega(int tiempoEntrega) {
    this.tiempoEntrega = tiempoEntrega;
  }
}
